/* FramedBox.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 * 
 * Copyright (C) 2009 DENIZET Calixte
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 * 
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 * 
 */

package org.scilab.forge.jlatexmath;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/**
 * A box representing a rotated box.
 */
public class FramedBox extends Box {

    protected Box box;
    protected double thickness;
    protected double space;
    private Color line;
    private Color bg;

    public FramedBox(Box box, double thickness, double space) {
        this.box = box;
        this.width = box.width + 2 * thickness + 2 * space;
        this.height = box.height + thickness + space;
        this.depth = box.depth + thickness + space;
        this.shift = box.shift;
        this.thickness = thickness;
        this.space = space;
    }

    public FramedBox(Box box, double thickness, double space, Color line, Color bg) {
        this(box, thickness, space);
        this.line = line;
        this.bg = bg;
    }

    public void draw(GraphicsContext g2, double x, double y) {
        Paint st = g2.getStroke();
        g2.setLineCap(StrokeLineCap.BUTT);
        g2.setLineWidth(thickness);
        g2.setLineJoin(StrokeLineJoin.MITER);

        double th = thickness / 2;
        if (bg != null) {
            Paint prev = g2.getFill();
            g2.setFill(bg);
            g2.fillRect(x + th, y - height + th, width - thickness, height + depth - thickness);
            g2.setFill(prev);
        }
        if (line != null) {
            Paint prev = g2.getStroke();
            g2.setStroke(line);
            g2.rect(x + th, y - height + th, width - thickness, height + depth - thickness);
            g2.setStroke(prev);
        } else {
            g2.rect(x + th, y - height + th, width - thickness, height + depth - thickness);

        }
        //drawDebug(g2, x, y);
        g2.setStroke(st);
        box.draw(g2, x + space + thickness, y);
    }

    public int getLastFontId() {
        return box.getLastFontId();
    }
}
