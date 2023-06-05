package com.ceo.publicservices.presentation.controller.mainmenu;

import com.ceo.publicservices.domain.enteties.MeterReading;
import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.domain.enteties.UserSingleton;
import com.ceo.publicservices.infrastructure.dao.MeterReadingDao;
import com.ceo.publicservices.infrastructure.dao.ServiceDao;
import com.ceo.publicservices.infrastructure.dao.impl.MeterReadingDaoImpl;
import com.ceo.publicservices.infrastructure.dao.impl.ServiceDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class InputMeters implements Initializable {
    @FXML
    public ComboBox<Service> picker;
    @FXML
    public TextField inputField;
    public DatePicker pickerData;

    ServiceDao dao = new ServiceDaoImpl();
    MeterReadingDao daoMeter = new MeterReadingDaoImpl();



    public void input(ActionEvent event) {
        int id = picker.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        String amount = inputField.getText();
        BigDecimal decimal = new BigDecimal(amount);
        Service service = dao.findById(id);
        System.out.println(service);
        MeterReading meter = new MeterReading();
        meter.setUser(UserSingleton.getInstance().getUser());
        meter.setService(service);
        meter.setConsumption(decimal);
        meter.setReadingDate(Date.valueOf(pickerData.getValue()));
        daoMeter.save(meter);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Service> service = dao.findAll();
        ObservableList<Service> observableList = FXCollections.observableList(service);
        picker.setItems(observableList);
        picker.setCellFactory(new Callback<ListView<Service>, ListCell<Service>>() {
            @Override
            public ListCell<Service> call(ListView<Service> param) {
                return new ListCell<Service>() {
                    @Override
                    protected void updateItem(Service item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName()+" " + item.getTariffs());

                        }
                    }
                };
            }
        });
    }
}
