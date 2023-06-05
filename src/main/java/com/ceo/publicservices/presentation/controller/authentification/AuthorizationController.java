package com.ceo.publicservices.presentation.controller.authentification;

import com.ceo.publicservices.domain.validation.LoginValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {


    @FXML
    public Pane pane;
    public Hyperlink linkAdmin;
    private LoginValidation validate = new LoginValidation();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Hyperlink link;

    @FXML
    public void goToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void login(ActionEvent event) {
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();

        if (validate.loginValidate(username,password) == true){
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


    }

    private void goTo(String fxml) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void goToAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/ceo/publicservices/view/admin-authorization.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
