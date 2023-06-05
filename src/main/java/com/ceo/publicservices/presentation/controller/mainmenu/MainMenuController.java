package com.ceo.publicservices.presentation.controller.mainmenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    public BorderPane pane;
    @FXML
    public VBox sidebarWrapper;

    public void showData(ActionEvent actionEvent) {
        pane.getChildren().clear();
        loadPage("user-info");
    }

    public void showCountBill(ActionEvent actionEvent) {
        pane.getChildren().clear();
        loadPage("count-bill");
    }

    public void addMeterReading(ActionEvent actionEvent) {
        pane.getChildren().clear();
        loadPage("input-meters");
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
        loadPage("user-info");


    }
}
