/* ScaleBox.java
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


import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.scene.text.TextLayout;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * A box representing a scaled box.
 */
public class JavaFontRenderingBox extends Box {

    private static Font font = Font.font("Serif", FontWeight.NORMAL, 10);

    private String str;
    private double size;
    private static Integer KERNING_ON;
    private static Integer LIGATURES_ON;

    private static TextLayout layout = Toolkit.getToolkit().getTextLayoutFactory().createLayout();

    static {
        layout.setWrapWidth(Float.MAX_VALUE);
        layout.setLineSpacing(2.0f);
    }

    static BaseBounds getBounds(Font font, String text) {
        layout.setContent(text != null ? text : "", font);
        return layout.getBounds();
    }


    public JavaFontRenderingBox(String str, FontWeight weight, double size, Font f, boolean kerning) {
        this.str = str;
        this.size = size;
        BaseBounds rect = getBounds(font, str);

        this.height = rect.getHeight() * size / 10;
        this.depth = (rect.getHeight() * size / 10) - this.height;
        this.width = rect.getWidth() * size / 10;
    }

    public JavaFontRenderingBox(String str, FontWeight type, double size) {
        this(str, type, size, font, true);
    }

    public static void setFont(String name) {
        font = Font.font("name", 10);
    }

    public void draw(GraphicsContext g2, double x, double y) {
        g2.translate(x, y);
        g2.scale(0.1 * size, 0.1 * size);
        g2.fillText(str, 0, 0);
        g2.scale(10 / size, 10 / size);
        g2.translate(-x, -y);
    }

    public int getLastFontId() {
        return 0;
    }
}
