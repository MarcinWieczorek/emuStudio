/*
 * PseudoIF.java
 *
 * Created on Sobota, 2007, september 29, 13:39
 *
 * KEEP IT SIMPLE, STUPID
 * some things just: YOU AREN'T GONNA NEED IT
 */

package treeZ80;

import impl.HEXFileHandler;
import impl.NeedMorePassException;
import impl.compileEnv;
import treeZ80Abstract.Expression;
import treeZ80Abstract.Pseudo;

/**
 *
 * @author vbmacher
 */
public class PseudoIF extends Pseudo {
    private Expression expr;
    private Program subprogram;
    private boolean condTrue; // => for pass4; if this is true,
                              // then generate code, otherwise not.
    
    /** Creates a new instance of PseudoIF */
    public PseudoIF(Expression expr, Program stat, int line, int column) {
        super(line,column);
        this.expr = expr;
        this.subprogram = stat;
        this.condTrue = false;
    }
    
    // if doesnt have and id
    public String getName() { return ""; }

    /// compile time ///
    
    public int getSize() {
        if (expr.getValue() != 0) return subprogram.getSize();
        else return 0;
    }

    public void pass1() throws Exception {
        subprogram.pass1();
    }
    
    public int pass2(compileEnv env, int addr_start) throws Exception {
        // now evaluate expression and then decide if block can be passed
        try {
            if (expr.eval(env, addr_start) != 0) {
                condTrue = true;
                return subprogram.pass2(env, addr_start);
            }
            else return addr_start;
        } catch (NeedMorePassException e) {
            throw new Exception("[" + line + "," + column
                    + "] IF expression can't be ambiguous");
        }
    }

    public void pass4(HEXFileHandler hex) throws Exception {
        if (condTrue) subprogram.pass4(hex);
    }
}
