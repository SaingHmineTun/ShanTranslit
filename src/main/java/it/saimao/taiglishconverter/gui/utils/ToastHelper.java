package it.saimao.taiglishconverter.gui.utils;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public final class ToastHelper {
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay) {
        final Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);
        toastStage.initModality(Modality.APPLICATION_MODAL);

        final Text text = new Text(toastMsg);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Roboto Condensed",20));

        final StackPane root = new StackPane(text);
        root.setPrefHeight(40);
        root.setPrefWidth(250);
        root.setStyle("-fx-background-color: #333131;");
        root.setPadding(new Insets(5));

        final Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        toastStage.setX(ownerStage.getX() + ((ownerStage.getWidth() / 2) - (root.getPrefWidth() / 2)));
        toastStage.setY(ownerStage.getY() + (ownerStage.getHeight() - 100));
        toastStage.setScene(scene);
        toastStage.show();

        // make effect: fade-in, pause, then fade-out effect varying the opacity of the whole window
        final javafx.animation.FadeTransition inTransition = new FadeTransition(new Duration(fadeInDelay),
                toastStage.getScene().getRoot());
        inTransition.setFromValue(0.0);
        inTransition.setToValue(1);

        final javafx.animation.FadeTransition outTransition = new FadeTransition(new Duration(fadeOutDelay),
                toastStage.getScene().getRoot());
        outTransition.setFromValue(1.0);
        outTransition.setToValue(0);

        final javafx.animation.PauseTransition pauseTransition = new PauseTransition(new Duration(toastDelay));

        final javafx.animation.SequentialTransition mainTransition = new SequentialTransition(
                inTransition, pauseTransition, outTransition);
        mainTransition.setOnFinished(ae -> toastStage.close());
        mainTransition.play();
    }
}
