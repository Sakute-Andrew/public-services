package com.ceo.publicservices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/authorization.fxml"));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/com/ceo/publicservices/img/icon.png"))));
        stage.setTitle("Services");
        stage.setScene( new Scene(root, 890, 520));
        stage.show();
    }

    public static void main() {
        launch();
    }
}