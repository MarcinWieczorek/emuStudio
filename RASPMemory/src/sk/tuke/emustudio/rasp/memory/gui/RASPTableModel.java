/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.emustudio.rasp.memory.gui;

import javax.swing.table.AbstractTableModel;
import sk.tuke.emustudio.rasp.memory.MemoryItem;
import sk.tuke.emustudio.rasp.memory.NumberMemoryItem;
import sk.tuke.emustudio.rasp.memory.OperandType;
import sk.tuke.emustudio.rasp.memory.RASPInstruction;
import sk.tuke.emustudio.rasp.memory.RASPMemoryContext;

/**
 * MODEL for the table with memory content.
 *
 * @author miso
 */
public class RASPTableModel extends AbstractTableModel {

    private final RASPMemoryContext memory;

    /**
     * Default constructor.
     *
     * @param memory the memory that will hold the content.
     */
    public RASPTableModel(final RASPMemoryContext memory) {
        this.memory = memory;
    }

    /**
     * Get number of items in memory.
     *
     * @return the number of items in memory
     */
    @Override
    public int getRowCount() {
        return memory.getSize();
    }

    /**
     * Get number of columns in memory table; it is 2: |"address" |"cell value"|
     *
     * @return 2
     */
    @Override
    public int getColumnCount() {
        return 2;
    }

    /**
     * Returns the string representation of cell in the memory table cell at
     * given position.
     *
     * @param rowIndex
     * @param columnIndex
     * @return string representation of memory table cell at given position
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //column with addresses, optionally marked with label
        if (columnIndex == 0) {
            String label = memory.getLabel(rowIndex);
            if (label != null) {
                return String.valueOf(rowIndex) + " " + label;
            } else {
                return String.valueOf(rowIndex);
            }
        } //column with cell value
        else {
            //get item at given position
            MemoryItem item = (MemoryItem) memory.read(rowIndex);
            //item is a number
            if (item instanceof NumberMemoryItem) {
                //if this is not the zeroth item (not the ACC)
                if (rowIndex != 0) {
                    //get the item at the previous position
                    MemoryItem previousItem = memory.read(rowIndex - 1);
                    //if previos item is an instruction
                    if (previousItem instanceof RASPInstruction) {
                        RASPInstruction instruction = (RASPInstruction) previousItem;
                        int code = instruction.getCode();
                        //if the instruction is a jump instruction
                        if (code == RASPInstruction.JMP || code == RASPInstruction.JZ || code == RASPInstruction.JGTZ) {
                            /*for sure, previous is a jump instruction, so this
                             item is an address, so look for corressponding label in memory*/
                            String label = memory.getLabel(((NumberMemoryItem) item).getValue());
                            if (label != null) {
                                return label;
                            } else {
                                //if no label at the address, simply return the number
                                return ((NumberMemoryItem) item).toString();
                            }
                        } else if (instruction.getOperandType() == OperandType.CONSTANT) {
                            return "= " + ((NumberMemoryItem) item).toString();
                        } else if (instruction.getOperandType() == OperandType.REGISTER) {
                            return ((NumberMemoryItem) item).toString();
                        }
                    } else {
                        //if previous item is not an instruction, simply return the number
                        return ((NumberMemoryItem) item).toString();
                    }

                } else {
                    //if item is the zeroth one, and is a number, it is definitelly not an operand, so just return it
                    return ((NumberMemoryItem) item).toString();
                }
            } else if (item instanceof RASPInstruction) {
                //if item is an instruction, just return its mnemonics
                return ((RASPInstruction) item).getCodeStr();
            }
        }
        return null;        
    }

    @Override
    public String getColumnName(int column) {
        return (column == 0) ? ("Address") : ("Cell Value");
    }

}
