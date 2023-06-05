package com.ceo.publicservices.presentation.controller.authentification;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @FXML
    public BorderPane borderPane;

    public void login(ActionEvent event) {
    }

    public void goToRegistration(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent root = null;
        try {
            root =  FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/authorization.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.getChildren().add(root);


    }
}
