/*
 * IdExpr.java
 *
 * Created on Streda, 2007, október 10, 15:50
 *
 * KEEP IT SIMPLE, STUPID
 * some things just: YOU AREN'T GONNA NEED IT
 *
 * Copyright (C) 2007-2010 Peter Jakubčo <pjakubco at gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package as_8080.tree8080;

import as_8080.impl.NeedMorePassException;
import as_8080.impl.compileEnv;
import as_8080.tree8080Abstract.ExprNode;

/**
 *
 * @author vbmacher
 */
public class IdExpr extends ExprNode {
    private String name;
    
    /** Creates a new instance of IdExpr */
    public IdExpr(String name) {
        this.name = name;
    }

    /// compile time ///
    public int getSize() { return 0; }

    public int eval(compileEnv env, int curr_addr) throws Exception {
        // identifier in expression can be only label, equ, or set statement. macro NOT
        // search in env for labels
        LabelNode lab = env.getLabel(this.name);
        if ((lab != null) && (lab.getAddress() == null))
            throw new NeedMorePassException(this, lab.getLine(), lab.getColumn());
        else if (lab != null) {
            this.value = lab.getAddress();
            return this.value;
        }

        EquPseudoNode equ = env.getEqu(this.name);
        if (equ != null) {
            this.value = equ.getValue();
            return this.value;
        }
        
        SetPseudoNode set = env.getSet(this.name);
        if (set != null) {
            this.value = set.getValue();
            return this.value;
        }
        else
            throw new Exception("Unknown identifier (" + this.name + ")");
    }
    
}
