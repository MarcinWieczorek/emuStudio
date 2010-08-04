/**
 * CompoundUndoManager.java
 *
 * KISS, YAGNI
 *
 * Copyright (C) 2009-2010 Peter Jakubčo <pjakubco at gmail.com>
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
package emustudio.gui.utils;

/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License 
 *       at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.  
 */

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;


/**
 * A simple UndoManager that groups the Edits in each 0.5 second.  If the time 
 * difference between the current undo and the last one is less than 0.5 secs,
 * then the two edits are compound.
 * @author Ayman Al-Sairafi
 */
@SuppressWarnings("serial")
public class CompoundUndoManager extends UndoManager {
    
    /**
     * Delay between consequtive edits in ms where edits are added together.
     * If the delay is greater than this, then separate undo operations are 
     * done, otherwise they are combined.
     */
    public static final int IDLE_DELAY_MS = 500;

    long startMillis = 0;
    CompoundEdit comp = null;

    public CompoundUndoManager() {}


    @Override
    public synchronized boolean addEdit(UndoableEdit anEdit) {
        long now = System.currentTimeMillis();
        if (comp == null) {
            comp = new CompoundEdit();
            super.addEdit(comp);
        }
        comp.addEdit(anEdit);
        if (now - startMillis > IDLE_DELAY_MS) {
            comp.end();
            comp = null;
        }
        startMillis = now;
        return true;
    }


    @Override
    public synchronized boolean canRedo() {
        commitCompound();
        return super.canRedo();
    }


    @Override
    public synchronized boolean canUndo() {
        commitCompound();
        return super.canUndo();
    }


    @Override
    public synchronized void discardAllEdits() {
        comp = null;
        super.discardAllEdits();
    }


    @Override
    public synchronized void redo() throws CannotRedoException {
        commitCompound();
        try { super.redo(); }
        catch (NullPointerException e) {}
    }


    @Override
    public synchronized void undo() throws CannotUndoException {
        commitCompound();
        try { super.undo(); }
        catch(NullPointerException e) {}
    }

    private void commitCompound() {
        if (comp != null) {
            comp.end();
            comp = null;
        }
    }
}
