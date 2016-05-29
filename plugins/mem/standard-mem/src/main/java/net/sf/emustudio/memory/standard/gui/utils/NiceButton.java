/*
 * KISS, YAGNI, DRY
 *
 * (c) Copyright 2006-2016, Peter Jakubčo
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
package net.sf.emustudio.memory.standard.gui.utils;

import javax.swing.*;
import java.awt.*;

public class NiceButton extends JButton {

    private final static int x_WIDTH = 125;
    private final static int x_HEIGHT = 30;

    private NiceButton() {
        super();
        Dimension d = getPreferredSize();
        d.setSize(x_WIDTH, x_HEIGHT);
        this.setPreferredSize(d);
        this.setSize(x_WIDTH, x_HEIGHT);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
    }

    public NiceButton(String text) {
        this();
        setText(text);
    }
}
