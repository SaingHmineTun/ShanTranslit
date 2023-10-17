package it.saimao.converter.shan_translit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ShanTranslitApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shan-translit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Shan Translit");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResource("/it/saimao/converter/shan_translit/images/icon-small.png").toExternalForm()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}