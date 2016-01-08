/*
 * Copyright (C) 2007-2015 Peter Jakubčo
 * KISS, YAGNI, DRY
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
package net.sf.emustudio.intel8080.assembler.tree;

import emulib.runtime.HEXFileManager;
import net.sf.emustudio.intel8080.assembler.exceptions.ValueTooBigException;
import net.sf.emustudio.intel8080.assembler.impl.CompileEnv;
import net.sf.emustudio.intel8080.assembler.treeAbstract.DataValueNode;
import net.sf.emustudio.intel8080.assembler.treeAbstract.ExprNode;
import net.sf.emustudio.intel8080.assembler.treeAbstract.OpCodeNode;

public class DBDataNode extends DataValueNode {

    private ExprNode expression = null;
    private String literalString = null;
    private OpCodeNode opcode = null;

    public DBDataNode(ExprNode expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public DBDataNode(String literalString, int line, int column) {
        super(line, column);
        this.literalString = literalString;
    }

    public DBDataNode(OpCodeNode opcode, int line, int column) {
        super(line, column);
        this.opcode = opcode;
    }

    /// compile time ///
    @Override
    public int getSize() {
        if (expression != null) {
            return 1;
        } else if (literalString != null) {
            return literalString.length();
        } else if (opcode != null) {
            return opcode.getSize();
        }
        return 0;
    }

    @Override
    public int pass2(CompileEnv env, int addr_start) throws Exception {
        if (expression != null) {
            expression.eval(env, addr_start);
            return addr_start + 1;
        } else if (literalString != null) {
            return addr_start + literalString.length();
        } else if (opcode != null) {
            return opcode.pass2(env, addr_start);
        }
        return addr_start;
    }

    @Override
    public void pass4(HEXFileManager hex) throws Exception {
        if (expression != null) {
            if (expression.getValue() > 0xFF) {
                throw new ValueTooBigException(line, column, expression.getValue(), 0xFF);
            }
            if (expression.getValue() < -128) {
                throw new ValueTooBigException(line, column, expression.getValue(), -128);
            }
            hex.putCode(expression.getEncValue(true));
        } else if (literalString != null) {
            hex.putCode(this.getEncString(literalString));
        } else if (opcode != null) {
            opcode.pass4(hex);
        }
    }
}
