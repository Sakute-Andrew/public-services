package com.ceo.publicservices.presentation.controller.authentification;

import com.ceo.publicservices.domain.validation.RegistrationValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {

    private RegistrationValidation validate = new RegistrationValidation();

    @FXML
    public Pane pane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Hyperlink link;
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public PasswordField repeatPasswordField;

    public void register(ActionEvent event) {
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        System.out.println(username + password + repeatPassword + firstName + lastName);

        if (validate.registerValidate(username, password,repeatPassword, firstName, lastName ) == true ){
            try {
                root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/main-menu.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        System.out.println("______________________________________________________________________________________");
    }

    public void goToAuthorization(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/authorization.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
