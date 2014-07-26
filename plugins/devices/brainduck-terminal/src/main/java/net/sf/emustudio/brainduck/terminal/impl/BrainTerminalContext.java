/*
 * BrainTerminalContext.java
 * 
 * Copyright (C) 2009-2012 Peter Jakubčo
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
package net.sf.emustudio.brainduck.terminal.impl;

import emulib.plugins.device.DeviceContext;
import net.sf.emustudio.brainduck.terminal.gui.BrainTerminalDialog;

public class BrainTerminalContext implements DeviceContext<Short> {

    private BrainTerminalDialog gui;

    public BrainTerminalContext(BrainTerminalDialog gui) {
        this.gui = gui;
    }

    @Override
    public Class<?> getDataType() {
        return Short.class;
    }

    @Override
    public Short read() {
        return (short) gui.getChar();
    }

    @Override
    public void write(Short val) {
        gui.putChar((char)(short)val);
    }

}