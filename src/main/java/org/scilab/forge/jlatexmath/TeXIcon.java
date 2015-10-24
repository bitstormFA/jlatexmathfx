/* TeXIcon.java
 * =========================================================================
 * This file is originally part of the JMathTeX Library - http://jmathtex.sourceforge.net
 *
 * Copyright (C) 2004-2007 Universiteit Gent
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

/* Modified by Calixte Denizet */


package org.scilab.forge.jlatexmath;


import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class TeXIcon {

    private static final Color defaultColor = Color.BLACK;

    public static double defaultSize = -1;
    public static double magFactor = 0;

    private Box box;

    private final double size;

    private Insets insets = new Insets(0, 0, 0, 0);

    private Paint fg = null;

    public boolean isColored = false;

    /**
     * Creates a new icon that will paint the given formula box in the given point size.
     *
     * @param b    the formula box to be painted
     * @param size the point size
     */
    protected TeXIcon(Box b, double size) {
        this(b, size, false);
    }

    protected TeXIcon(Box b, double size, boolean trueValues) {
        box = b;

        if (defaultSize != -1) {
            size = defaultSize;
        }

        if (magFactor != 0) {
            this.size = size * Math.abs(magFactor);
        } else {
            this.size = size;
        }

        /* I add this little value because it seems that tftopl calculates badly
           the height and the depth of certains characters.
        */
        if (!trueValues) {
            insets = new Insets(Math.round(0.18f * size),
                    Math.round(0.18f * size),
                    Math.round(0.18f * size),
                    Math.round(0.18f * size));
        }
    }

    public void setForeground(Paint fg) {
        this.fg = fg;
    }

    /**
     * Get the insets of the TeXIcon.
     *
     * @return the insets
     */
    public Insets getInsets() {
        return insets;
    }

    /**
     * Set the insets of the TeXIcon.
     *
     * @param insets     the insets
     * @param trueValues true to force the true values
     */
    public void setInsets(Insets insets, boolean trueValues) {
        this.insets = insets;
        if (!trueValues) {
            insets = new Insets(0.18f * size, 0.18f * size, 0.18f * size, 0.18f * size);
        }
    }

    /**
     * Set the insets of the TeXIcon.
     *
     * @param insets the insets
     */
    public void setInsets(Insets insets) {
        setInsets(insets, false);
    }

    /**
     * Change the width of the TeXIcon. The new width must be greater than the current
     * width, otherwise the icon will remain unchanged. The formula will be aligned to the
     * left ({@linkplain TeXConstants#ALIGN_LEFT}), to the right
     * ({@linkplain TeXConstants#ALIGN_RIGHT}) or will be centered
     * in the middle ({@linkplain TeXConstants#ALIGN_CENTER}).
     *
     * @param width     the new width of the TeXIcon
     * @param alignment a horizontal alignment constant: LEFT, RIGHT or CENTER
     */
    public void setIconWidth(int width, int alignment) {
        double diff = width - getIconWidth();
        if (diff > 0)
            box = new HorizontalBox(box, box.getWidth() + diff, alignment);
    }

    /**
     * Change the height of the TeXIcon. The new height must be greater than the current
     * height, otherwise the icon will remain unchanged. The formula will be aligned on top
     * (TeXConstants.TOP), at the bottom (TeXConstants.BOTTOM) or will be centered
     * in the middle (TeXConstants.CENTER).
     *
     * @param height    the new height of the TeXIcon
     * @param alignment a vertical alignment constant: TOP, BOTTOM or CENTER
     */
    public void setIconHeight(int height, int alignment) {
        double diff = height - getIconHeight();
        if (diff > 0)
            box = new VerticalBox(box, diff, alignment);
    }

    /**
     * Get the total height of the TeXIcon. This also includes the insets.
     */
    public int getIconHeight() {
        return ((int) ((box.getHeight()) * size + 0.99 + insets.getTop())) + ((int) ((box.getDepth()) * size + 0.99 + insets.getBottom()));
    }

    /**
     * Get the total height of the TeXIcon. This also includes the insets.
     */
    public int getIconDepth() {
        return (int) (box.getDepth() * size + 0.99 + insets.getBottom());
    }

    /**
     * Get the total width of the TeXIcon. This also includes the insets.
     */

    public int getIconWidth() {
        return (int) (box.getWidth() * size + 0.99 + insets.getLeft() + insets.getRight());
    }

    public double getTrueIconHeight() {
        return (box.getHeight() + box.getDepth()) * size;
    }

    /**
     * Get the total height of the TeXIcon. This also includes the insets.
     */
    public double getTrueIconDepth() {
        return box.getDepth() * size;
    }

    /**
     * Get the total width of the TeXIcon. This also includes the insets.
     */

    public double getTrueIconWidth() {
        return box.getWidth() * size;
    }

    public double getBaseLine() {
        return (double) ((box.getHeight() * size + 0.99 + insets.getTop()) /
                ((box.getHeight() + box.getDepth()) * size + 0.99 + insets.getTop() + insets.getBottom()));
    }

    public Box getBox() {
        return box;
    }

    public void paintInCanvas(Canvas canvas, int x, int y, Paint fg,Paint bg) {
        GraphicsContext g2 = canvas.getGraphicsContext2D();
        g2.save();
        g2.scale(size, size);

        g2.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        if (null != bg) {
            g2.setFill(bg);
            g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        }

        if(null!=fg) {
            box.foreground=fg;
        }

        if (null == fg) {
            g2.setFill(defaultColor);
        } else {
            g2.setFill(fg);
        }

        box.draw(g2, (x + insets.getLeft()) / size, (y + insets.getTop()) / size + box.getHeight());
        g2.restore();
    }

}
