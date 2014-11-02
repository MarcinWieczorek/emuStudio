/*
 * Copyright (C) 2014 Peter Jakubčo
 * KISS, DRY, YAGNI
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.sf.emustudio.brainduck.terminal.io;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyListener;

public class GUIUtils {

    public static void addListenerRecursively(Component c, KeyListener listener) {
        c.addKeyListener(listener);
        if (c instanceof Container) {
            Container cont = (Container) c;
            Component[] children = cont.getComponents();
            for (Component child : children) {
                addListenerRecursively(child, listener);
            }
        }
    }

    public static void removeListenerRecursively(Component c, KeyListener listener) {
        c.removeKeyListener(listener);
        if (c instanceof Container) {
            Container cont = (Container) c;
            Component[] children = cont.getComponents();
            for (Component child : children) {
                removeListenerRecursively(child, listener);
            }
        }
    }
    
}
