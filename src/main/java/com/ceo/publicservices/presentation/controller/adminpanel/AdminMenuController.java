package com.ceo.publicservices.presentation.controller.adminpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    public BorderPane pane;

    public void showAllUsers(ActionEvent event) {
        loadPage("all-users");
    }

    public void billCount(ActionEvent event) {
        loadPage("make-bill");

    }

    public void showServices(ActionEvent event) {
        loadPage("services");
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/" + page + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("all-users");

    }
}
