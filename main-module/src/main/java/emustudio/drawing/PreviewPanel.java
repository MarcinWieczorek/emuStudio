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
package emustudio.drawing;

import emulib.runtime.UniversalFileFilter;
import emustudio.main.Main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreviewPanel extends JPanel {
    private final static Logger logger = LoggerFactory.getLogger(PreviewPanel.class);

    private Schema schema;
    private int schemaWidth;
    private int schemaHeight;
    private File lastImageFile;
    /**
     * Left factor is a constant used in panel resizing. It is a distance between panel left and the x position of the
     * nearest point in the schema.
     */
    private int leftFactor;
    /**
     * Top factor is a constant used in panel resizing. It is a distance between panel top and the y position of the
     * nearest point in the schema.
     */
    private int topFactor;
    /* double buffering */
    private Image dbImage;   // second buffer
    private Graphics2D dbg;  // graphics for double buffering
    private boolean panelResized;

    public PreviewPanel() {
        this(null);
    }

    public PreviewPanel(Schema schema) {
        super.setBackground(Color.WHITE);
        super.setDoubleBuffered(true);

        this.schema = schema;
        leftFactor = topFactor = 0;
        panelResized = false;
    }

    @Override
    public void update(Graphics g) {
        // initialize buffer if needed
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width,
                    this.getSize().height);
            dbg = (Graphics2D) dbImage.getGraphics();
        }
        // clear screen in background
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width,
                this.getSize().height);

        // draw elements in background
        dbg.setColor(getForeground());
        paint(dbg);

        // draw image on the screen
        g.drawImage(dbImage, 0, 0, this);
    }

    private void resizePanel(Graphics g) {
        if (schema == null) {
            return;
        }
        // hladanie najvzdialenejsich elementov (alebo bodov lebo ciara
        // nemoze byt dalej ako bod)
        int width = 0, height = 0, minLeft = -1, minTop = -1;

        schema.getAllElements().forEach((elem) -> {
            elem.measure(g);
        });

        for (Element elem : schema.getAllElements()) {
            int eX = elem.getX() - elem.getWidth() / 2;
            int eY = elem.getY() - elem.getHeight() / 2;
            int eWidth = elem.getWidth();
            int eHeight = elem.getHeight();

            if (minLeft == -1) {
                minLeft = eX;
            } else if (minLeft > eX) {
                minLeft = eX;
            }

            if (minTop == -1) {
                minTop = eY;
            } else if (minTop > eY) {
                minTop = eY;
            }

            if (eX + eWidth > width) {
                width = eX + eWidth;
            }
            if (eY + eHeight > height) {
                height = eY + eHeight;
            }
        }
        for (int i = schema.getConnectionLines().size() - 1; i >= 0; i--) {
            List<Point> ps = schema.getConnectionLines().get(i).getPoints();
            for (int j = ps.size() - 1; j >= 0; j--) {
                Point p = ps.get(j);

                if (minLeft == -1) {
                    minLeft = p.x;
                } else if (minLeft > p.x) {
                    minLeft = p.x;
                }

                if (minTop == -1) {
                    minTop = p.y;
                } else if (minTop > p.y) {
                    minTop = p.y;
                }

                if (p.x > width) {
                    width = p.x;
                }
                if (p.y > height) {
                    height = p.y;
                }
            }
        }
        leftFactor = minLeft - Schema.MIN_LEFT_MARGIN;
        topFactor = minTop - Schema.MIN_TOP_MARGIN;
        if (width != 0 && height != 0) {
            this.setSize(width - leftFactor + Schema.MIN_LEFT_MARGIN,
                    height - topFactor + Schema.MIN_TOP_MARGIN);
            this.revalidate();
        }
        schemaWidth = width;
        schemaHeight = height;
        panelResized = true;
    }

    private int getSchemaWidth() {
        return schemaWidth;
    }

    private int getSchemaHeight() {
        return schemaHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (schema == null) {
            return;
        }

        Graphics2D graphics = (Graphics2D) g;

        boolean moved = panelResized;
        if (!panelResized) {
            resizePanel(g);
        }
        if (!moved) {
            schema.selectAll();
            schema.moveSelection(-leftFactor, -topFactor);
            schema.deselectAll();
            schema.getAllElements().forEach((elem) -> {
                elem.measure(g);
            });
        }
        schema.getConnectionLines().forEach((line) -> {
            line.draw(graphics, true);
        });
        schema.getAllElements().forEach((element) -> {
            element.draw(graphics);
        });
    }

    public void setSchema(Schema s) {
        if (s == null) {
            return;
        }
        this.schema = s;
        panelResized = false;
        this.repaint();
    }

    public void clearScreen() {
        this.schema = null;
        this.repaint();
    }

    public void saveSchemaImage() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Save schema image");
        fileChooser.setAcceptAllFileFilterUsed(false);

        ImageIO.scanForPlugins();
        UniversalFileFilter filter = new UniversalFileFilter();
        filter.addExtension(".png");
        filter.setDescription("PNG file");
        
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        
        fileChooser.setApproveButtonText("Save");
        
        if (lastImageFile != null) {
            fileChooser.setCurrentDirectory(lastImageFile.getParentFile());
        } else {
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        }
        fileChooser.setSelectedFile(null);

        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selectedFile = fileChooser.getSelectedFile();
        if (!selectedFile.getName().toLowerCase().endsWith(".png")) {
            selectedFile = new File(selectedFile.getAbsolutePath() + ".png");
        }
        lastImageFile = selectedFile;

        // Save the image
        BufferedImage bi = new BufferedImage(getSchemaWidth(), getSchemaHeight(), BufferedImage.TYPE_INT_RGB);
        paint(bi.createGraphics());
        try {
            ImageIO.write(bi, "png", lastImageFile);
        } catch (IOException e) {
            logger.error("Could not save schema image.", e);
            Main.tryShowErrorMessage("Could not save schema image. See log file for details.");
        }
    }
}
