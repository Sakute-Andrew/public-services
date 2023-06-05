package com.ceo.publicservices.presentation.controller.adminpanel;

import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.infrastructure.dao.ServiceDao;
import com.ceo.publicservices.infrastructure.dao.impl.ServiceDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class EditService  {
    public Label setPriceName;
    public Button input;
    public TextField inputPrice;
    private Service service;

    static AlertWindow alertWindow = new AlertWindow();
    ServiceDao dao = new ServiceDaoImpl();

    public void setService(Service service) {
        this.service = service;
    }


    public void input(ActionEvent event) {

        if (inputPrice.getText().isEmpty()){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поле не може бути пустим!");

        }
        BigDecimal decimalValue = new BigDecimal(inputPrice.getText());
        service.setTariffs(decimalValue);
        dao.update(service);
        alertWindow.showAlert(Alert.AlertType.INFORMATION, "Успіх!",
                "Ціну встановлено на " + decimalValue);

    }
}
