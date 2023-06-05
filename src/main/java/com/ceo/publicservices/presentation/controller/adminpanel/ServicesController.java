package com.ceo.publicservices.presentation.controller.adminpanel;

import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.infrastructure.dao.ServiceDao;
import com.ceo.publicservices.infrastructure.dao.impl.ServiceDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ServicesController implements Initializable {
    public TableView<Service> allUsersTable;
    public TableColumn<Service, Integer> id;
    public TableColumn<Service, String> name;
    public TableColumn<Service, String> description;
    public TableColumn<Service, BigDecimal> tarrifs;
    public TableColumn icon;

    ServiceDao dao = new ServiceDaoImpl();

    static AlertWindow alertWindow = new AlertWindow();

    Service service = new Service();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Service> services = FXCollections.observableList(dao.findAll());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tarrifs.setCellValueFactory(new PropertyValueFactory<>("tariffs"));

        Callback<TableColumn<Service, String>, TableCell<Service, String>> cellFoctory = (TableColumn<Service, String> param) -> {
            // make cell containing buttons
            return new TableCell<Service, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button delete = new Button("Видалити");
                        Button edit = new Button("Редагувати");

                        delete.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        edit.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        delete.setOnMouseClicked((MouseEvent event) -> {

                            try {

                                int id  = allUsersTable.getSelectionModel().getSelectedItem().getId();
                                System.out.println(id);
                                dao.delete(id);

                            } catch (Exception ex) {

                            }

                        });
                        edit.setOnMouseClicked((MouseEvent event) -> {

                            if (allUsersTable.getSelectionModel().isEmpty()){
                                alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                                        "Ви не обрали поле!");

                            }else {
                                try {
                                    Service service = allUsersTable.getSelectionModel().getSelectedItem();
                                    System.out.println(service);
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ceo/publicservices/view/edit-service.fxml"));
                                    Parent parent = loader.load();
                                    EditService controller = loader.getController();
                                    controller.setService(service);

                                    Scene scene = new Scene(parent);
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.initStyle(StageStyle.UTILITY);
                                    stage.show();

                                } catch (IOException ex) {
                                    System.out.println("-------service-----");
                                }
                            }

                        });
                        HBox managebtn = new HBox(edit, delete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(delete, new Insets(2, 2, 0, 3));
                        HBox.setMargin(edit, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
        };
        icon.setCellFactory(cellFoctory);
        allUsersTable.setItems(services);


    }

    public void input(ActionEvent event) {

    }

    public void newService(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ceo/publicservices/view/new-service.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
}
