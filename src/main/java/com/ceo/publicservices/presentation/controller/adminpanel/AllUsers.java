package com.ceo.publicservices.presentation.controller.adminpanel;

import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.infrastructure.dao.UserDao;
import com.ceo.publicservices.infrastructure.dao.impl.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class  AllUsers implements Initializable {
    public TableView<User> allUsersTable;
    public TableColumn<User, Integer> userId;
    public TableColumn<User, String> username;
    public TableColumn<User, String> firstName;
    public TableColumn<User, String> lastName;
    public TableColumn<User, String> accountNumber;
    public TableColumn icon;

    UserDao dao = new UserDaoImpl();

    User user = new User();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<User> meterReadings = FXCollections.observableList(dao.findAll());
        userId.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            return new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button delete = new Button("Видалити");


                        delete.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );

                        delete.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                int id  = allUsersTable.getSelectionModel().getSelectedItem().getId();
                                System.out.println(id);
                                dao.delete(id);

                            } catch (Exception ex) {

                            }

                        });


                        HBox managebtn = new HBox( delete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(delete, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
        };
        icon.setCellFactory(cellFoctory);
        allUsersTable.setItems(meterReadings);



    }
}
