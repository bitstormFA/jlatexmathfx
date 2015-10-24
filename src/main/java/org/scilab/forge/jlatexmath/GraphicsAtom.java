/* GraphicsAtom.java
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

import javafx.scene.image.Image;

import java.io.File;
import java.util.Map;

/**
 * An atom representing an atom containing a graphic.
 */
public class GraphicsAtom extends Atom {

    private Image image = null;
    private double w, h;

    private Atom base;
    private boolean first = true;

    public GraphicsAtom(String path, String option) {
        File f = new File(path);
        if (!f.exists()) {
            image = null;
        } else {
            image = new Image(path);
        }

        draw();
        buildAtom(option);
    }

    protected void buildAtom(String option) {
        base = this;
        Map<String, String> options = ParseOption.parseMap(option);
        if (options.containsKey("width") || options.containsKey("height")) {
            base = new ResizeAtom(base, options.get("width"), options.get("height"), options.containsKey("keepaspectratio"));
        }
        if (options.containsKey("scale")) {
            double scl = Double.parseDouble(options.get("scale"));
            base = new ScaleAtom(base, scl, scl);
        }
        if (options.containsKey("angle") || options.containsKey("origin")) {
            base = new RotateAtom(base, options.get("angle"), options.get("origin"));
        }
        if (options.containsKey("interpolation")) {
            String meth = options.get("interpolation");
        }
    }

    public void draw() {
        if (image != null) {
            w = image.getWidth();
            h = image.getHeight();
        }
    }

    public Box createBox(TeXEnvironment env) {
        if (image != null) {
            if (first) {
                first = false;
                return base.createBox(env);
            } else {
                env.isColored = true;
                double width = w * SpaceAtom.getFactor(TeXConstants.UNIT_PIXEL, env);
                double height = h * SpaceAtom.getFactor(TeXConstants.UNIT_PIXEL, env);
                return new GraphicsBox(image, width, height, env.getSize());
            }
        }

        return new TeXFormula("\\text{ No such image file ! }").root.createBox(env);
    }
}