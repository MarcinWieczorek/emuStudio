/*
 * KISS, YAGNI, DRY
 *
 * (c) Copyright 2006-2017, Peter Jakubčo
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
package net.sf.emustudio.memory.standard.gui;

import net.sf.emustudio.memory.standard.gui.model.MemoryTableModel;
import net.sf.emustudio.memory.standard.gui.model.TableMemory;

import javax.swing.SpinnerModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class KeyboardHandler extends KeyAdapter {
    private final TableMemory tblMemory;
    private final SpinnerModel spnPageModel;
    private final MemoryTableModel memoryModel;
    private final MemoryDialog memDialog;
    
    private boolean right_correct;
    private boolean left_correct;
    private boolean up_correct;
    private boolean down_correct;

    public KeyboardHandler(TableMemory tblMemory, SpinnerModel spnPageModel, MemoryDialog memDialog) {
        this.tblMemory = Objects.requireNonNull(tblMemory);
        this.spnPageModel = Objects.requireNonNull(spnPageModel);
        this.memDialog = Objects.requireNonNull(memDialog);
        this.memoryModel = tblMemory.getMemModel();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        final int columnCount = tblMemory.getColumnCount();
        final int rowCount = tblMemory.getRowCount();

        final int selectedColumn = tblMemory.getSelectedColumn();
        final int selectedRow = tblMemory.getSelectedRow();
        
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_RIGHT) && (selectedColumn == columnCount - 1)) {
            if (selectedRow == rowCount - 1) {
                int i = (Integer) spnPageModel.getValue();
                try {
                    memoryModel.setPage(i + 1);
                } catch (IndexOutOfBoundsException ex) {
                    spnPageModel.setValue(0);
                }
                tblMemory.setRowSelectionInterval(0, 0);
                tblMemory.scrollRectToVisible(tblMemory.getCellRect(0, 0, true));
            } else {
                tblMemory.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
                right_correct = true;
            }
        } else if ((key == KeyEvent.VK_LEFT) && (selectedColumn == 0)) {
            if (selectedRow == 0) {
                int i = (Integer) spnPageModel.getValue();
                try {
                    memoryModel.setPage(i - 1);
                } catch (IndexOutOfBoundsException ex) {
                    spnPageModel.setValue(memoryModel.getPageCount() - 1);
                }
                tblMemory.setRowSelectionInterval(rowCount - 1, rowCount - 1);
                left_correct = true;
            } else {
                tblMemory.setRowSelectionInterval(selectedRow  - 1, selectedRow - 1);
                left_correct = true;
            }
        } else if ((key == KeyEvent.VK_UP) && (selectedRow == 0)) {
            int i = (Integer) spnPageModel.getValue();
            try {
                memoryModel.setPage(i - 1);
            } catch (IndexOutOfBoundsException ex) {
                spnPageModel.setValue(memoryModel.getPageCount() - 1);
            }
            tblMemory.setColumnSelectionInterval(selectedColumn, selectedColumn);
            up_correct = true;
        } else if ((key == KeyEvent.VK_DOWN) && (selectedRow == rowCount - 1)) {
            int i = (Integer) spnPageModel.getValue();
            try {
                memoryModel.setPage(i + 1);
            } catch (IndexOutOfBoundsException ex) {
                spnPageModel.setValue(0);
            }
            tblMemory.setColumnSelectionInterval(selectedColumn, selectedColumn);
            down_correct = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        final int columnCount = tblMemory.getColumnCount();
        final int rowCount = tblMemory.getRowCount();
        
        final int selectedColumn = tblMemory.getSelectedColumn();
        final int selectedRow = tblMemory.getSelectedRow();
        
        if (right_correct) {
            try {
                tblMemory.setColumnSelectionInterval(0, 0);
            } catch (Exception ex) {
            }
            right_correct = false;
        }
        if (left_correct) {
            try {
                tblMemory.setColumnSelectionInterval(columnCount - 1, columnCount - 1);
            } catch (Exception ex) {
            }
            left_correct = false;
        }
        if (up_correct) {
            try {
                tblMemory.setRowSelectionInterval(rowCount - 1, rowCount - 1);
            } catch (Exception ex) {
            }
            tblMemory.scrollRectToVisible(tblMemory.getCellRect(selectedRow, selectedColumn, true));
            up_correct = false;
        }
        if (down_correct) {
            try {
                tblMemory.setRowSelectionInterval(0, 0);
            } catch (Exception ex) {
            }
            tblMemory.scrollRectToVisible(tblMemory.getCellRect(selectedRow, selectedColumn, true));
            down_correct = false;
        }
        memDialog.updateMemVal(selectedRow, selectedColumn);
    }
}
