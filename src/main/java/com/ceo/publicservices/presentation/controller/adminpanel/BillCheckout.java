package com.ceo.publicservices.presentation.controller.adminpanel;

import com.ceo.publicservices.domain.enteties.Bill;
import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.infrastructure.dao.BillDao;
import com.ceo.publicservices.infrastructure.dao.impl.BillDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class BillCheckout {

    public TextField inputPrice;
    public DatePicker pickerData;
    private Service service;
    private User user;
    private BigDecimal consumption;

    static AlertWindow alertWindow = new AlertWindow();
    
    BillDao dao = new BillDaoImpl();
    Bill bill = new Bill();


    public void setService(Service service) {
        this.service = service;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }
    public void input(ActionEvent event) {

        if (inputPrice.getText().isEmpty() || pickerData.getValue() == null){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поля не може бути пустими!");

        }
        bill.setUser(user);
        bill.setService(service);
        bill.setBillDate(Date.valueOf(pickerData.getValue()));
        bill.setAmount(consumption.multiply(service.getTariffs()));
        bill.setBillStatus(inputPrice.getText());
        dao.save(bill);
        alertWindow.showAlert(Alert.AlertType.INFORMATION, "Успішно!",
                "Квитанцію на рахунок "+ user.getAccountNumber()+" успішно виписано!");



    }
}
