/*
 * AddressValueNode.java
 *
 * Created on Sobota, 2007, september 29, 10:07
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

import as_8080.impl.CompileEnv;
import as_8080.tree8080Abstract.ExprNode;

/**
 *
 * @author vbmacher
 */
public class AddressValueNode extends ExprNode {
    
    public void setAddress(int address) {
        this.value = address;
    }
    
    public int getAddress() { return value; }
    
    /// compile time ///
    
    //??
    @Override
    public int eval(CompileEnv env, int curr_addr) {
        this.setAddress(curr_addr);
        return curr_addr;
    }

}
