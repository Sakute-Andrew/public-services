package com.ceo.publicservices.presentation.controller.mainmenu;

import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.infrastructure.dao.ServiceDao;
import com.ceo.publicservices.infrastructure.dao.impl.ServiceDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CountBill implements Initializable {
    public TextField amount;
    public ComboBox<Service> picker;
    public Text toCount;

    ServiceDao dao = new ServiceDaoImpl();

    static AlertWindow alertWindow = new AlertWindow();
    
    public void input(ActionEvent event) {
        if (amount.getText().isEmpty()){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поля не можуть бути пустими!");
        }
        BigDecimal count = picker.getSelectionModel().getSelectedItem().getTariffs();
        String amountText = amount.getText();
        BigDecimal total = new BigDecimal(amountText);
        BigDecimal result = count.multiply(total);
        toCount.setText("До сплати: " + result.toString());

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
