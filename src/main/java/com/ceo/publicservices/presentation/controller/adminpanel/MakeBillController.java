package com.ceo.publicservices.presentation.controller.adminpanel;

import com.ceo.publicservices.domain.enteties.MeterReading;
import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.infrastructure.dao.MeterReadingDao;
import com.ceo.publicservices.infrastructure.dao.impl.MeterReadingDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Date;
import java.util.ResourceBundle;

public class MakeBillController implements Initializable {
    public TableView<MeterReading> allUsersTable;
    public TableColumn<MeterReading, Integer> id;
    public TableColumn<MeterReading, String> userId;
    public TableColumn<MeterReading, String> serviceId;
    public TableColumn<MeterReading, Date> readingDate;
    public TableColumn<MeterReading, BigDecimal> consumption;
    public TableColumn icon;
    public TableColumn<MeterReading, String> consumptionType;

    static AlertWindow alertWindow = new AlertWindow();



    MeterReadingDao dao = new MeterReadingDaoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<MeterReading> meterReadings = FXCollections.observableList(dao.findAll());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        userId.setCellValueFactory(cellData -> {
            MeterReading meterReading = cellData.getValue();
            String username = meterReading.getUser().getUsername();
            return new SimpleStringProperty(username);
        });
        serviceId.setCellValueFactory(cellData -> {
            MeterReading meterReading = cellData.getValue();
            String serviceName = meterReading.getService().getName();
            return new SimpleStringProperty(serviceName);
        });
        readingDate.setCellValueFactory(new PropertyValueFactory<>("readingDate"));
        consumption.setCellValueFactory(new PropertyValueFactory<>("consumption"));
        consumptionType.setCellValueFactory(cellData -> {
            MeterReading meterReading = cellData.getValue();
            String serviceType = meterReading.getService().getDescription();
            return new SimpleStringProperty(serviceType);
        });
        Callback<TableColumn<MeterReading, String>, TableCell<MeterReading, String>> cellFoctory = (TableColumn<MeterReading, String> param) -> {
            // make cell containing buttons
            return new TableCell<MeterReading, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button delete = new Button("Видалити");
                        Button edit = new Button("Виписати");

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
                                    Service service = allUsersTable.getSelectionModel().getSelectedItem().getService();
                                    User user = allUsersTable.getSelectionModel().getSelectedItem().getUser();
                                    BigDecimal consumption = allUsersTable.getSelectionModel().getSelectedItem().getConsumption();
                                    System.out.println(service);
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ceo/publicservices/view/bill-checkout.fxml"));
                                    Parent parent = loader.load();
                                    BillCheckout controller = loader.getController();
                                    controller.setService(service);
                                    controller.setUser(user);
                                    controller.setConsumption(consumption);
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
        allUsersTable.setItems(meterReadings);


    }
}
