package com.ceo.publicservices.presentation.controller.adminpanel;

import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.infrastructure.dao.ServiceDao;
import com.ceo.publicservices.infrastructure.dao.impl.ServiceDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class NewService {

    public TextField inputType;
    public TextField inputMeasureUnit;
    public TextField inputPrice;

    Service service = new Service();
    ServiceDao dao = new ServiceDaoImpl();

    static AlertWindow alertWindow = new AlertWindow();

    public void input(ActionEvent event) {
        if (inputPrice.getText().isEmpty() || inputMeasureUnit.getText().isEmpty() || inputType.getText().isEmpty()){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поле не може бути пустим!");
        }
        service.setName(inputType.getText());
        service.setDescription(inputMeasureUnit.getText());
        BigDecimal decimalValue = new BigDecimal(inputPrice.getText());
        service.setTariffs(decimalValue);
        dao.save(service);
        alertWindow.showAlert(Alert.AlertType.INFORMATION, "Успіх!",
                "Послугу додано!");


    }
}
