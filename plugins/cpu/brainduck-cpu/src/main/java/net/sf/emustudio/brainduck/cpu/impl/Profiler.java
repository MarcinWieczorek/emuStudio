package net.sf.emustudio.brainduck.cpu.impl;

import emulib.plugins.memory.MemoryContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_COPY_AND_CLEAR;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_DEC;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_DECV;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_INC;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_INCV;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_LOOP_END;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_LOOP_START;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_PRINT;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_READ;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_SCANLOOP;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_STOP;

public class Profiler {
    private final MemoryContext<Short> memory;
    private final CachedOperation[] operationsCache;
    private final Integer[] loopEndsCache;
    
    private int repeatedOptsCount = 0;
    private int copyLoopsCount = 0;
    private int scanLoopsCount = 0;

    public static final class CachedOperation {
        public enum TYPE { COPYLOOP, REPEAT, SCANLOOP }

        final TYPE type;
        int nextIP;

        CachedOperation(TYPE type) {
            this.type = type;
        }

        // for repeats
        int argument;
        short operation;

        // for copyloops
        List<CopyLoop> copyLoops;

        @Override
        public String toString() {
            switch (type) {
                case COPYLOOP: return type + copyLoops.toString();
                case REPEAT: return type + "[op=" + operation + ", arg=" + argument + "]";
            }
            return "UNKNOWN";
        }
    }

    public final static class CopyLoop {
        int factor;
        int relativePosition;
        
        short specialOP;

        CopyLoop(int factor, int relativePosition) {
            this.factor = factor;
            this.relativePosition = relativePosition;
        }

        @Override
        public String toString() {
            return "[f=" + factor + ",pos=" + relativePosition + "]";
        }
    }

    Profiler(MemoryContext<Short> memory) {
        this.memory = Objects.requireNonNull(memory);

        int size = memory.getSize();

        loopEndsCache = new Integer[size];
        operationsCache = new CachedOperation[size];

        reset();
    }

    public void reset() {
        Arrays.fill(loopEndsCache, -1);
        Arrays.fill(operationsCache, null);
        repeatedOptsCount = 0;
        copyLoopsCount = 0;
        scanLoopsCount = 0;
    }

    void profileAndOptimize(int programSize) {
        optimizeLoops(programSize);
        optimizeCopyLoops(programSize);
        optimizeScanLoops(programSize);
        optimizeRepeatingOperations(programSize);
        
        System.out.println(this);
    }
    
    private void optimizeRepeatingOperations(int programSize) {
        int lastOperation = -1;
        short OP;
        for (int tmpIP = 0; tmpIP < programSize; tmpIP++) {
            OP = memory.read(tmpIP);
            if (OP != I_LOOP_START && OP != I_LOOP_END && (lastOperation == OP)) {
                int previousIP = tmpIP - 1;
                CachedOperation operation = new CachedOperation(CachedOperation.TYPE.REPEAT);

                operation.operation = OP;
                operation.argument = 2;

                while ((tmpIP+1) < programSize && (memory.read(tmpIP+1) == lastOperation)) {
                    operation.argument++;
                    tmpIP++;
                }
                operation.nextIP = tmpIP + 1;
                if (operationsCache[previousIP] == null) {
                    operationsCache[previousIP] = operation;
                    repeatedOptsCount++;
                }
            }
            lastOperation = OP;
        }
    }

    private void optimizeLoops(int programSize) {
        short OP;
        for (int tmpIP = 0; tmpIP < programSize; tmpIP++) {
            if (memory.read(tmpIP) != I_LOOP_START) {
                continue;
            }
            int loop_count = 0; // loop nesting level counter

            // we start to look for "]" instruction
            // on the same nesting level (according to loop_count value)
            // IP is pointing at following instruction
            int tmpIP2 = tmpIP + 1;
            while ((tmpIP2 < programSize) && (OP = memory.read(tmpIP2++)) != I_STOP) {
                if (OP == I_LOOP_START) {
                    loop_count++;
                }
                if (OP == I_LOOP_END) {
                    if (loop_count == 0) {
                        loopEndsCache[tmpIP] = tmpIP2;
                        break;
                    } else {
                        loop_count--;
                    }
                }
            }
        }
    }

    private void optimizeCopyLoops(int programSize) {
        short OP;
        for (int tmpIP = 0; tmpIP < programSize; tmpIP++) {
            OP = memory.read(tmpIP);
            if (OP == I_LOOP_START && tmpIP+2 < programSize) {
                tmpIP++;
                OP = memory.read(tmpIP);
                CachedOperation copyLoop = findCopyLoop(tmpIP, OP);

                if (copyLoop != null) {
                    operationsCache[tmpIP - 1] = copyLoop;
                    tmpIP = copyLoop.nextIP;
                    copyLoopsCount++;
                }
            }
        }
    }

    private int[] repeatRead(short incOp, short decOp, int pos, int stopPos, int var) {
        if (pos >= stopPos) {
            return new int[] { pos, var};
        }
        do {
            short op = memory.read(pos);
            if (op == incOp) {
                var++;
            } else if (op == decOp) {
                var--;
            } else break;
            pos++;
        } while (pos < stopPos);
        return new int[] { pos, var };
    }
    
    private int readSpecial(int startIP, int stopIP, List<CopyLoop> copyLoops) {
        if (startIP > stopIP) {
            return startIP;
        }
        do {
            short specialOP = memory.read(startIP);
            if (specialOP == I_PRINT || specialOP == I_READ) {
                CopyLoop c = new CopyLoop(0, 0);
                c.specialOP = specialOP;
                copyLoops.add(c);
                startIP++;
            } else break;
        } while (startIP < stopIP);
        return startIP;
    }
    
    private CachedOperation findCopyLoop(int tmpIP, short OP) {
        if (loopEndsCache[tmpIP - 1] == -1) {
            return null; // we don't have optimized loops
        }

        int startIP = tmpIP;
        int stopIP = loopEndsCache[tmpIP - 1] - 1;
        int nextIP = stopIP + 1;

        // first find [-  ...] or [... -]
        if (OP == I_DECV) {
            startIP++;
        } else if (memory.read(stopIP - 1) == I_DECV) {
            stopIP--;
        } else {
            return null; // not a copy-loop
        }

        // now identify the copyloops. General scheme:
        // 1. pointer increments / decrements
        //   2. value updates
        // 3. pointer decrements / increments in reverse order
        // 4. repeat - basically on the end the pointer should be on the same position

        int pointerInvEntrophy = 0;
        List<CopyLoop> copyLoops = new ArrayList<>();

        while (startIP < stopIP) {
            startIP = readSpecial(startIP, stopIP, copyLoops);
            
            int[] posVar = repeatRead(I_INC, I_DEC, startIP, stopIP, pointerInvEntrophy);
            if (posVar[0] == startIP) {
                break; // weird stuff
            }
            startIP = posVar[0];
            pointerInvEntrophy = posVar[1];

            if (pointerInvEntrophy == 0) {
                break;
            }

            int[] posFactor = repeatRead(I_INCV, I_DECV, startIP, stopIP, 0);
            if (posFactor[0] == startIP) {
                break; // weird stuff
            }
            startIP = posFactor[0];

            copyLoops.add(new CopyLoop(posFactor[1],pointerInvEntrophy));
        }
        if (pointerInvEntrophy == 0 && startIP == stopIP) {
            CachedOperation operation = new CachedOperation(CachedOperation.TYPE.COPYLOOP);
            operation.copyLoops = copyLoops;
            operation.nextIP = nextIP;
            operation.operation = I_COPY_AND_CLEAR;
            return operation;
        }
        return null;
    }
    
    private void optimizeScanLoops(int programSize) {
        short OP;
        for (int tmpIP = 0; tmpIP < programSize; tmpIP++) {
            OP = memory.read(tmpIP);
            if (OP == I_LOOP_START) {
                if (loopEndsCache[tmpIP] == -1) {
                    // loop optimization is needed
                    break;
                }
                int loopEnd = loopEndsCache[tmpIP]- 1;
                int posVar[] = repeatRead(I_INC, I_DEC, tmpIP+1, loopEnd, 0);
                
                if (posVar[0] == loopEnd) {
                    // we have scanloop
                    CachedOperation scanLoop = new CachedOperation(CachedOperation.TYPE.SCANLOOP);
                    scanLoop.nextIP = posVar[0] + 1;
                    scanLoop.argument = posVar[1];
                    scanLoop.operation = I_SCANLOOP;
                    
                    operationsCache[tmpIP] = scanLoop;
                    tmpIP = scanLoop.nextIP;
                    scanLoopsCount++;
                }
            }
        }
    }

    CachedOperation findCachedOperation(int address) {
        return operationsCache[address];
    }

    Integer findLoopEnd(int address) {
        return loopEndsCache[address];
    }

    @Override
    public String toString() {
        int total = repeatedOptsCount + copyLoopsCount + scanLoopsCount;
        return "Profiler{optimizations=" + total
                + ", repeatedOps=" + repeatedOptsCount + ", copyLoops=" + copyLoopsCount
                + ", scanLoops=" + scanLoopsCount
                + "}";
    }
}