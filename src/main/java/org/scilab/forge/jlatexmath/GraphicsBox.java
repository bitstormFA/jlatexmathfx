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
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

/**
 * A box representing a box containing a graphics.
 */
public class GraphicsBox extends Box {

    private Image image;
    private double scl;

    public GraphicsBox(Image image, double width, double height, double size) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.scl = 1 / size;
        depth = 0;
        shift = 0;
    }

    public void draw(GraphicsContext g2, double x, double y) {
        Affine oldAt = g2.getTransform();

        g2.translate(x, y - height);
        g2.scale(scl, scl);
        g2.drawImage(image, 0, 0);
        g2.setTransform(oldAt);
    }

    public int getLastFontId() {
        return 0;
    }
}