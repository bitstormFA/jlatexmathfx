package org.scilab.forge.jlatexmath;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class GCStroke {

    double width;
    StrokeLineCap cap;
    StrokeLineJoin join;
    double miterlimit;

    public GCStroke(double width, StrokeLineCap cap, StrokeLineJoin join, double miterlimit) {
        this.width = width;
        this.cap = cap;
        this.join = join;
        this.miterlimit = miterlimit;
    }

    public GCStroke(GraphicsContext gc) {
        this.width = gc.getLineWidth();
        this.cap = gc.getLineCap();
        this.join = gc.getLineJoin();
        this.miterlimit = gc.getMiterLimit();
    }

    public void setStroke(GraphicsContext gc) {
        gc.setLineWidth(width);
        gc.setLineCap(cap);
        gc.setLineJoin(join);
        gc.setMiterLimit(miterlimit);
    }
}
