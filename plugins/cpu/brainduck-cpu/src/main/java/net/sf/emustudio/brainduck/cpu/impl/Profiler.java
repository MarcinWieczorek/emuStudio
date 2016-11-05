package net.sf.emustudio.brainduck.cpu.impl;

import emulib.plugins.memory.MemoryContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_COPY_AND_CLEAR;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_DEC;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_DECV;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_INC;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_INCV;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_LOOP_END;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_LOOP_START;
import static net.sf.emustudio.brainduck.cpu.impl.EmulatorEngine.I_STOP;

public class Profiler {
    private final MemoryContext<Short> memory;
    private final Map<Integer, CachedOperation> operationsCache = new HashMap<>();
    private final Map<Integer, Integer> loopEndsCache = new HashMap<>();

    public static final class CachedOperation {
        enum TYPE { COPYLOOP, REPEAT };

        public final TYPE type;
        public int nextIP;

        public CachedOperation(TYPE type) {
            this.type = type;
        }

        // for repeats
        public int argument;
        public short operation;

        // for copyloops
        public List<CopyLoop> copyLoops;

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

        public CopyLoop(int factor, int relativePosition) {
            this.factor = factor;
            this.relativePosition = relativePosition;
        }

        @Override
        public String toString() {
            return "[f=" + factor + ",pos=" + relativePosition + "]";
        }
    }

    public Profiler(MemoryContext<Short> memory) {
        this.memory = Objects.requireNonNull(memory);
    }

    public void reset() {
        operationsCache.clear();
    }

    public void optimizeRepeatingOperations(int programSize) {
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
                if (!operationsCache.containsKey(previousIP)) {
                    operationsCache.put(previousIP, operation);
                }
            }
            lastOperation = OP;
        }
    }

    public void optimizeLoops(int programSize) {
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
                        loopEndsCache.put(tmpIP, tmpIP2);
                        break;
                    } else {
                        loop_count--;
                    }
                }
            }
        }
    }

    public void optimizeCopyLoops(int programSize) {
        short OP;
        for (int tmpIP = 0; tmpIP < programSize; tmpIP++) {
            OP = memory.read(tmpIP);
            if (OP == I_LOOP_START && tmpIP+2 < programSize) {
                tmpIP++;
                OP = memory.read(tmpIP);
                CachedOperation copyLoop = findCopyLoop(tmpIP, OP);

                if (copyLoop != null) {
                    operationsCache.put(tmpIP - 1, copyLoop);
                    tmpIP = copyLoop.nextIP;
                    continue;
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
        } while (true);
        return new int[] { pos, var };
    }

    private CachedOperation findCopyLoop(int tmpIP, short OP) {
        if (!loopEndsCache.containsKey(tmpIP - 1)) {
            return null; // we don't have optimized loops
        }

        int startIP = tmpIP;
        int stopIP = loopEndsCache.get(tmpIP - 1) - 1;
        int nextIP = stopIP + 1;

        // first find [-  ...] or [... -]
        if (OP == I_DECV) {
            startIP++;
        } else if (memory.read(stopIP - 1) == I_DECV) {
            stopIP--;
        }

        // now identify the copyloops. General scheme:
        // 1. pointer increments / decrements
        //   2. value updates
        // 3. pointer decrements / increments in reverse order
        // 4. repeat - basically on the end the pointer should be on the same position

        int pointerInvEntrophy = 0;
        List<CopyLoop> copyLoops = new ArrayList<>();

        while (startIP < stopIP) {
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

    public CachedOperation findCachedOperation(int address) {
        return operationsCache.get(address);
    }

    public Integer findLoopEnd(int address) {
        return loopEndsCache.get(address);
    }

    @Override
    public String toString() {
        return "Profiler{optimizations=" + operationsCache.size() + "}";
    }
}
