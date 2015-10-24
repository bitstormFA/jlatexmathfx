/* OvalBox.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 * 
 * Copyright (C) 2011 DENIZET Calixte
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
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/**
 * A box representing a rotated box.
 */
public class OvalBox extends FramedBox {

    private float shadowRule;

    public OvalBox(FramedBox fbox) {
        super(fbox.box, fbox.thickness, fbox.space);
    }

    public void draw(GraphicsContext g2, double x, double y) {
        box.draw(g2, x + space + thickness, y);
        g2.save();
        double th = thickness / 2;
        GCStroke s = new GCStroke(thickness, StrokeLineCap.BUTT, StrokeLineJoin.MITER, g2.getMiterLimit());
        s.setStroke(g2);
        double r = 0.5f * Math.min(width - thickness, height + depth - thickness);
        g2.strokeRoundRect(x + th, y - height + th, width - thickness, height + depth - thickness, r, r);
        g2.restore();
    }

    public int getLastFontId() {
        return box.getLastFontId();
    }
}
