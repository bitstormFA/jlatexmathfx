/* ScaleAtom.java
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


import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


/**
 * The string rendering is made in using Java Graphics2D.drawString.
 */
public class JavaFontRenderingAtom extends Atom {

    private String str;
    private FontWeight weight = FontWeight.NORMAL;
    private FontPosture posture = FontPosture.REGULAR;
    private TeXFormula.FontInfos fontInfos;

    public JavaFontRenderingAtom(String str, FontWeight weight, FontPosture posture) {
        this.str = str;
        this.weight = weight;
        this.posture = posture;
    }

    public JavaFontRenderingAtom(String str, FontWeight weight) {
        this(str, weight, FontPosture.REGULAR);
    }

    public JavaFontRenderingAtom(String str, TeXFormula.FontInfos fontInfos) {
        this(str, FontWeight.NORMAL);
        this.fontInfos = fontInfos;
    }

    public Box createBox(TeXEnvironment env) {
        if (fontInfos == null) {
            return new JavaFontRenderingBox(str, weight, DefaultTeXFont.getSizeFactor(env.getStyle()));
        } else {
            DefaultTeXFont dtf = (DefaultTeXFont) env.getTeXFont();
            posture = dtf.isIt ? FontPosture.ITALIC : FontPosture.REGULAR;
            weight = dtf.isBold ? FontWeight.BOLD : FontWeight.NORMAL;
            boolean kerning = dtf.isRoman;
            Font font;
            if (dtf.isSs) {
                if (fontInfos.sansserif == null) {
                    font = Font.font(fontInfos.serif, FontWeight.NORMAL, 10);
                } else {
                    font = Font.font(fontInfos.sansserif, FontWeight.NORMAL, 10);
                }
            } else {
                if (fontInfos.serif == null) {
                    font = Font.font(fontInfos.sansserif, FontWeight.NORMAL, 10);
                } else {
                    font = Font.font(fontInfos.serif, FontWeight.NORMAL, 10);
                }
            }
            return new JavaFontRenderingBox(str, weight, DefaultTeXFont.getSizeFactor(env.getStyle()), font, kerning);
        }
    }
}
