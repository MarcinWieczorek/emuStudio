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

import net.jcip.annotations.ThreadSafe;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@ThreadSafe
public class Display extends JPanel {
    private final static int DEFAULT_COLUMN = 120;

    private final ConcurrentMap<Integer, int[]> memory = new ConcurrentHashMap<>();

    private final Cursor cursor;
    private volatile boolean needMeasure;
    private volatile int charWidth;
    private volatile int charHeight;

    private final Font textFont = new Font("Monospaced", 0, 14);

    public Display() {
        this.cursor = new Cursor(1, TimeUnit.SECONDS);

        this.setBackground(java.awt.Color.WHITE);
        this.setForeground(java.awt.Color.BLACK);
        this.setFont(textFont);
        this.setDoubleBuffered(true);
        needMeasure = true;
    }

    public void start() {
        measureIfNeeded();
        cursor.start(this);
    }

    public void stop() {
        cursor.stop();
        memory.clear();
    }

    public Cursor getTextCanvasCursor() {
        return cursor;
    }

    public void writeAtCursor(int c) {
        Point cursorPoint = cursor.getLogicalPoint();
        writeCharAt(c, cursorPoint.x, cursorPoint.y);
        cursor.advance(1);
    }

    private void ensureMemorySize(int x, int y) {
        if (!memory.containsKey(y)) {
            memory.putIfAbsent(y, new int[DEFAULT_COLUMN]);
        }
        int[] row = memory.get(y);
        if (row.length <= x) {
            int[] newRow = Arrays.copyOf(row, x + 20);
            while (!memory.replace(y, row, newRow)) {
                row = memory.get(y);
                if (row.length > x) {
                    break;
                }
                newRow = Arrays.copyOf(row, x + 20);
            }
        }
    }

    public void writeCharAt(int c, int x, int y) {
        ensureMemorySize(x, y);
        memory.get(y)[x] = c;
        repaint();
    }

    public void clear() {
        memory.clear();
        cursor.reset();
        repaint();
    }

    private void measureIfNeeded() {
        if (needMeasure) {
            FontMetrics fontMetrics = getGraphics().getFontMetrics();
            charWidth = fontMetrics.charWidth('W');
            charHeight = fontMetrics.getHeight();
            needMeasure = false;
        }
    }

    public int getCharWidth() {
        return charWidth;
    }

    public int getCharHeight() {
        return charHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setFont(textFont);
        g.clearRect(0, 0, getSize().width, getSize().height);

        int y = charHeight;
        int x = 0;

        int rowIndex = 0;
        for (Map.Entry<Integer, int[]> entry : memory.entrySet()) {
            int targetRow = entry.getKey();

            y += (charHeight * (targetRow - rowIndex));
            rowIndex = targetRow;

            int[] row = entry.getValue();
            x = 0;
            for (int col = 0; col < row.length; col++) {
                if (row[col] < 32) {
                    continue;
                }
                g.drawString("" + (char) row[col], x, y);
                x += charWidth;
            }
        }
    }

}
