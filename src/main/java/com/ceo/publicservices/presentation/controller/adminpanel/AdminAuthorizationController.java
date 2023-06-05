package com.ceo.publicservices.presentation.controller.adminpanel;

import com.ceo.publicservices.domain.validation.AdminValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminAuthorizationController {

    AdminValidation validate = new AdminValidation();
    public TextField usernameField;
    public PasswordField passwordField;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void login(ActionEvent event) {
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();

        if (validate.loginValidate(username, password) == true) {
            try {
                root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/main-admin-menu.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);

        }
    }
    public void goBack(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/authorization.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



    }
}
