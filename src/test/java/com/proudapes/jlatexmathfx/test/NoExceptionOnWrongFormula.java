package com.proudapes.jlatexmathfx.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.proudapes.jlatexmathfx.Control.LateXMathControl;

public class NoExceptionOnWrongFormula extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String latex = "\\beginError{array}{l}";
        VBox pane=new VBox();
        LateXMathControl lc=new LateXMathControl(latex);
        lc.setSize(24);
        pane.getChildren().addAll(lc);

        Scene scene=new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
