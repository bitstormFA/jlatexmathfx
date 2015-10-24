/* GraphicsBox.java
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
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/**
 * A box representing a box containing a graphics.
 */
public class GeoGebraLogoBox extends Box {

    private static final Color gray = new Color(102, 102, 102, 1);
    private static final Color blue = new Color(153, 153, 255, 1);

    private static final GCStroke st = new GCStroke(3.79999995f, StrokeLineCap.BUTT, StrokeLineJoin.MITER, 4f);
    private static final GCStroke stC = new GCStroke(1f, StrokeLineCap.BUTT, StrokeLineJoin.MITER, 4f);

    public GeoGebraLogoBox(double w, double h) {
        this.depth = 0;
        this.height = h;
        this.width = w;
        this.shift = 0;
    }

    public void draw(GraphicsContext g2, double x, double y) {
        g2.save();
        GCStroke oldS = new GCStroke(g2);

        g2.translate(x + 0.25f * height / 2.15f, y - 1.75f / 2.15f * height);
        g2.setStroke(gray);
        st.setStroke(g2);

        g2.scale(0.05f * height / 2.15f, 0.05f * height / 2.15f);
        g2.getTransform().appendRotation(-26 * Math.PI / 180.0, 20.5, 17.5);
        g2.arc(0, 0, 43, 32, 0, 360);
        g2.getTransform().appendRotation(26 * Math.PI / 180, 20.5, 17.5);
        oldS.setStroke(g2);

        drawCircle(g2, 16f, -5f);
        drawCircle(g2, -1f, 7f);
        drawCircle(g2, 5f, 28f);
        drawCircle(g2, 27f, 24f);
        drawCircle(g2, 36f, 3f);

        g2.restore();
    }

    private static void drawCircle(GraphicsContext g2, double x, double y) {
        g2.setStroke(blue);
        g2.translate(x, y);
        g2.fillArc(0, 0, 8, 8, 0, 360, ArcType.ROUND);
        g2.setStroke(Color.BLACK);
        g2.arc(0, 0, 8, 8, 0, 360);
        g2.translate(-x, -y);
    }

    public int getLastFontId() {
        return 0;
    }
}