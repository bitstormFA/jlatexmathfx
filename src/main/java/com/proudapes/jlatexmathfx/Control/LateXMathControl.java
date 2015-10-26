package com.proudapes.jlatexmathfx.Control;


import com.sun.javafx.css.converters.PaintConverter;
import com.sun.javafx.css.converters.SizeConverter;
import com.sun.javafx.css.converters.StringConverter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.WritableValue;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LateXMathControl extends Control {


    private static final String DEFAULT_FORMULA = "";
    private static final Color DEFAULT_BG = Color.TRANSPARENT;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final double DEFAULT_SIZE = Font.getDefault().getSize();


    public LateXMathControl(String text) {
        formulaProperty().set(text);
    }

    public LateXMathControl() {
        this("");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new LateXMathSkin(this);
    }

    //properties
    //-----------------------------

    public final StringProperty formulaProperty() {
        if (formula == null) {
            formula = new StyleableStringProperty(DEFAULT_FORMULA) {
                @Override
                public Object getBean() {
                    return LateXMathControl.this;
                }

                @Override
                public String getName() {
                    return "formula";
                }

                @Override
                public CssMetaData<LateXMathControl, String> getCssMetaData() {
                    return StyleableProperties.FORMULA;
                }
            };
        }
        return formula;
    }

    private StringProperty formula;

    public final void setFormula(String value) {
        formulaProperty().set((value == null) ? DEFAULT_FORMULA : value);
    }

    public final String getFormula() {
        return formula == null ? DEFAULT_FORMULA : formula.get();
    }


    //-----------------------------

    private ObjectProperty<Paint> bgColor;

    public final void setBgColor(Paint value) {
        bgColorProperty().set(value);
    }

    public final Paint getBgColor() {
        return bgColor == null ? DEFAULT_BG : bgColor.get();
    }

    public final ObjectProperty<Paint> bgColorProperty() {
        if (bgColor == null) {
            bgColor = new StyleableObjectProperty<Paint>(DEFAULT_BG) {

                @Override
                public CssMetaData<LateXMathControl, Paint> getCssMetaData() {
                    return StyleableProperties.BG_COLOR;
                }

                @Override
                public Object getBean() {
                    return LateXMathControl.this;
                }

                @Override
                public String getName() {
                    return "bgColor";
                }
            };
        }
        return bgColor;
    }

    //-----------------------------

    private ObjectProperty<Paint> textColor;

    public final void setTextColor(Paint value) {
        textColorProperty().set(value);
    }

    public final Paint getTextColor() {
        return textColor == null ? DEFAULT_COLOR : textColor.get();
    }

    public final ObjectProperty<Paint> textColorProperty() {
        if (textColor == null) {
            textColor = new StyleableObjectProperty<Paint>(DEFAULT_COLOR) {

                @Override
                public CssMetaData<LateXMathControl, Paint> getCssMetaData() {
                    return StyleableProperties.TEXT_COLOR;
                }

                @Override
                public Object getBean() {
                    return LateXMathControl.this;
                }

                @Override
                public String getName() {
                    return "textColor";
                }
            };
        }
        return textColor;
    }


    //-----------------------------

    public final DoubleProperty sizeProperty() {
        if (size == null) {
            size = new StyleableDoubleProperty(DEFAULT_SIZE) {

                @Override
                public CssMetaData<LateXMathControl, Number> getCssMetaData() {
                    return StyleableProperties.SIZE;
                }

                @Override
                public Object getBean() {
                    return LateXMathControl.this;
                }

                @Override
                public String getName() {
                    return "size";
                }
            };
        }
        return size;
    }

    private DoubleProperty size;

    public final void setSize(double value) {
        sizeProperty().setValue(value);
    }

    public final double getSize() {
        return size == null ? DEFAULT_SIZE : size.getValue();
    }

    //-----------------------------

    private static class StyleableProperties {
        private static final CssMetaData<LateXMathControl, String> FORMULA =
                new CssMetaData<LateXMathControl, String>("-fx-formula",
                        StringConverter.getInstance(), DEFAULT_FORMULA) {

                    @Override
                    public boolean isSettable(LateXMathControl n) {
                        return n.formula == null || !n.formula.isBound();
                    }

                    @Override
                    public StyleableProperty<String> getStyleableProperty(LateXMathControl n) {
                        return (StyleableProperty<String>) (WritableValue<String>) n.formulaProperty();
                    }
                };

        private static final CssMetaData<LateXMathControl, Paint> TEXT_COLOR =
                new CssMetaData<LateXMathControl, Paint>("-fx-text-color",
                        PaintConverter.getInstance(), DEFAULT_COLOR) {

                    @Override
                    public boolean isSettable(LateXMathControl n) {
                        return n.textColor == null || !n.textColor.isBound();
                    }

                    @Override
                    public StyleableProperty<Paint> getStyleableProperty(LateXMathControl n) {
                        return (StyleableProperty<Paint>) (WritableValue<Paint>) n.textColorProperty();
                    }
                };

        private static final CssMetaData<LateXMathControl, Paint> BG_COLOR =
                new CssMetaData<LateXMathControl, Paint>("-fx-bg",
                        PaintConverter.getInstance(), DEFAULT_BG) {

                    @Override
                    public boolean isSettable(LateXMathControl n) {
                        return n.bgColor == null || !n.bgColor.isBound();
                    }

                    @Override
                    public StyleableProperty<Paint> getStyleableProperty(LateXMathControl n) {
                        return (StyleableProperty<Paint>) (WritableValue<Paint>) n.bgColorProperty();
                    }
                };


        private static final CssMetaData<LateXMathControl, Number> SIZE =
                new CssMetaData<LateXMathControl, Number>("-fx-size",
                        SizeConverter.getInstance(), DEFAULT_SIZE) {

                    @Override
                    public boolean isSettable(LateXMathControl n) {
                        return n.size == null || !n.size.isBound();
                    }

                    @Override
                    public StyleableProperty<Number> getStyleableProperty(LateXMathControl n) {
                        return (StyleableProperty<Number>) (WritableValue<Number>) n.sizeProperty();
                    }
                };


        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<CssMetaData<? extends Styleable, ?>>(Control.getClassCssMetaData());
            Collections.addAll(styleables,
                    FORMULA,
                    TEXT_COLOR,
                    SIZE,
                    BG_COLOR
            );
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    //-----------------------------


    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }


    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }

}
