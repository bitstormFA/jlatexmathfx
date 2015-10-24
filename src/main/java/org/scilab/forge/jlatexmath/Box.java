/* Box.java
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

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.LinkedList;

public abstract class Box {

    public static boolean DEBUG = false;

    /**
     * The foreground color of the whole box. Child boxes can override this color.
     * If it's null and it has a parent box, the foreground color of the parent will
     * be used. If it has no parent, the foreground color of the component on which it
     * will be painted, will be used.
     */
    protected Paint foreground;

    /**
     * The background color of the whole box. Child boxes can paint a background on top of
     * this background. If it's null, no background will be painted.
     */
    protected Paint background;

    private Paint prevColor; // used temporarily in startDraw and endDraw

    /**
     * The width of this box, i.e. the value that will be used for further
     * calculations.
     */
    protected double width = 0;

    /**
     * The height of this box, i.e. the value that will be used for further
     * calculations.
     */
    protected double height = 0;

    /**
     * The depth of this box, i.e. the value that will be used for further
     * calculations.
     */
    protected double depth = 0;

    /**
     * The shift amount: the meaning depends on the particular kind of box
     * (up, down, left, right)
     */
    protected double shift = 0;

    protected int type = -1;

    /**
     * List of child boxes
     */
    protected LinkedList<Box> children = new LinkedList<>();
    protected Box parent;
    protected Box elderParent;


    /**
     * Inserts the given box at the end of the list of child boxes.
     *
     * @param b the box to be inserted
     */
    public void add(Box b) {
        children.add(b);
        b.parent = this;
        b.elderParent = elderParent;
    }

    /**
     * Inserts the given box at the given position in the list of child boxes.
     *
     * @param pos the position at which to insert the given box
     * @param b   the box to be inserted
     */
    public void add(int pos, Box b) {
        children.add(pos, b);
        b.parent = this;
        b.elderParent = elderParent;
    }

    /**
     * Creates an empty box (no children) with all dimensions set to 0 and no
     * foreground and background color set (default values will be used: null)
     */
    protected Box() {
        this(null, null);
    }

    /**
     * Creates an empty box (no children) with all dimensions set to 0 and sets
     * the foreground and background color of the box.
     *
     * @param fg the foreground color
     * @param bg the background color
     */
    protected Box(Paint fg, Paint bg) {
        foreground = fg;
        background = bg;
    }

    public void setParent(Box parent) {
        this.parent = parent;
    }

    public Box getParent() {
        return parent;
    }

    public void setElderParent(Box elderParent) {
        this.elderParent = elderParent;
    }

    public Box getElderParent() {
        return elderParent;
    }

    /**
     * Get the width of this box.
     *
     * @return the width of this box
     */
    public double getWidth() {
        return width;
    }

    public void negWidth() {
        width = -width;
    }

    /**
     * Get the height of this box.
     *
     * @return the height of this box
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get the depth of this box.
     *
     * @return the depth of this box
     */
    public double getDepth() {
        return depth;
    }

    /**
     * Get the shift amount for this box.
     *
     * @return the shift amount
     */
    public double getShift() {
        return shift;
    }

    /**
     * Set the width for this box.
     *
     * @param w the width
     */
    public void setWidth(double w) {
        width = w;
    }

    /**
     * Set the depth for this box.
     *
     * @param d the depth
     */
    public void setDepth(double d) {
        depth = d;
    }

    /**
     * Set the height for this box.
     *
     * @param h the height
     */
    public void setHeight(double h) {
        height = h;
    }

    /**
     * Set the shift amount for this box.
     *
     * @param s the shift amount
     */
    public void setShift(double s) {
        shift = s;
    }

    /**
     * Paints this box at the given coordinates using the given graphics context.
     *
     * @param g2 the graphics (2D) context to use for painting
     * @param x  the x-coordinate
     * @param y  the y-coordinate
     */
    public abstract void draw(GraphicsContext g2, double x, double y);

    /**
     * Get the id of the font that will be used the last when this box will be painted.
     *
     * @return the id of the last font that will be used.
     */
    public abstract int getLastFontId();

    /**
     * Stores the old color setting, draws the background of the box (if not null)
     * and sets the foreground color (if not null).
     *
     * @param g2 the graphics (2D) context
     * @param x  the x-coordinate
     * @param y  the y-coordinate
     */
    protected void startDraw(GraphicsContext g2, double x, double y) {

        g2.save();

        if (foreground != null) {
            g2.setFill(foreground);
        }
    }


    /**
     * Restores the previous color setting.
     *
     * @param g2 the graphics (2D) context
     */
    protected void endDraw(GraphicsContext g2) {

        g2.restore();
    }
}
