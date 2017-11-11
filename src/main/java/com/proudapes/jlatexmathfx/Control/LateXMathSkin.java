package com.proudapes.jlatexmathfx.Control;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.SkinBase;
import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class LateXMathSkin extends SkinBase<LateXMathControl> {

    protected Canvas canvas = null;
    protected boolean invalid = true;
    private TeXIcon teXIcon = null;

    public LateXMathSkin(LateXMathControl control) {
        super(control);
        updateTeXIcon();

        control.widthProperty().addListener(observable -> invalid = true);
        control.heightProperty().addListener(observable -> invalid = true);
        control.backgroundProperty().addListener(observable -> {
            invalid = true;
            getSkinnable().requestLayout();
        });
        control.textColorProperty().addListener(observable -> {
            invalid = true;
            getSkinnable().requestLayout();
        });
        control.sizeProperty().addListener(observable -> {
            invalid = true;
            updateTeXIcon();
            getSkinnable().requestLayout();
        });
        control.formulaProperty().addListener(observable -> {
            invalid = true;
            updateTeXIcon();
            getSkinnable().requestLayout();
        });
    }

    protected void updateTeXIcon() {
        try {

            TeXFormula teXFormula = new TeXFormula(getSkinnable().getFormula());
            teXIcon = teXFormula.createTeXIcon(TeXConstants.STYLE_DISPLAY, getSkinnable().getSize() / 11);
            teXIcon.setInsets(new Insets(1, 1, 1, 1));
        }
        catch (ParseException p) {
            getSkinnable().setFormula("\\text{Invalid Formula}");
            TeXFormula teXFormula = new TeXFormula(getSkinnable().getFormula());
            teXIcon = teXFormula.createTeXIcon(TeXConstants.STYLE_DISPLAY, getSkinnable().getSize() / 11);
            teXIcon.setInsets(new Insets(1, 1, 1, 1));
        }

        getSkinnable().requestLayout();
    }


    public void updateCanvas() {
        LateXMathControl control = getSkinnable();

        if (null == canvas) {
            canvas = new Canvas();
            getChildren().add(canvas);
        }

        teXIcon.paintInCanvas(canvas, 0, 0, control.getTextColor(), control.getBgColor());
        canvas.setWidth(teXIcon.getIconWidth());
        canvas.setHeight(teXIcon.getIconHeight());
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        if (invalid) {
            updateCanvas();
            invalid = false;
        }
        layoutInArea(canvas, contentX, contentY, contentWidth, contentHeight, -1, HPos.CENTER, VPos.CENTER);
    }


    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        LateXMathControl control = getSkinnable();
        Double size = control.getSize();
        return rightInset + leftInset + teXIcon.getIconWidth() * size;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        LateXMathControl control = getSkinnable();
        Double size = control.getSize();
        return topInset + bottomInset + teXIcon.getIconHeight() * size;
    }


}
