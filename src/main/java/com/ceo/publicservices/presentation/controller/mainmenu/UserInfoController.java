package com.ceo.publicservices.presentation.controller.mainmenu;

import com.ceo.publicservices.domain.enteties.Bill;
import com.ceo.publicservices.domain.enteties.MeterReading;
import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.domain.enteties.UserSingleton;
import com.ceo.publicservices.infrastructure.dao.BillDao;
import com.ceo.publicservices.infrastructure.dao.MeterReadingDao;
import com.ceo.publicservices.infrastructure.dao.impl.BillDaoImpl;
import com.ceo.publicservices.infrastructure.dao.impl.MeterReadingDaoImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    @FXML
    public Text welcome;
    @FXML
    public Text accountId;
    @FXML
    public TableView<MeterReading> meterReading;
    @FXML
    public TableColumn<MeterReading, String> service;
    @FXML
    public TableColumn<MeterReading, Date> date;
    @FXML
    public TableColumn<MeterReading, BigDecimal> amount;
    @FXML
    public TableColumn<MeterReading, String> in;
    public TableView<Bill> bill;
    public TableColumn<Bill, String> serviceName;
    public TableColumn<Bill, Date> dateBill;
    public TableColumn<Bill, BigDecimal> amountBill;
    public TableColumn<Bill,String> billStatus;

    MeterReadingDao dao = new MeterReadingDaoImpl();
    BillDao billDao = new BillDaoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = UserSingleton.getInstance().getUser();
        welcome.setText("Вітаємо у додатку, " + user.getFirstName() +" "+ user.getLastName());
        accountId.setText("Ваш особовий рахунок №" + user.getAccountNumber());
        loadDate(user);
    }

    private void loadDate(User user){
        ObservableList<MeterReading> meterReadings = FXCollections.observableList(dao.findByUsername(user.getId()));
        service.setCellValueFactory(cellData -> {
            MeterReading meterReading = cellData.getValue();
            String serviceName = meterReading.getService().getName();
            return new SimpleStringProperty(serviceName);
        });
        date.setCellValueFactory(new PropertyValueFactory<>("readingDate"));
        amount.setCellValueFactory(new PropertyValueFactory<>("consumption"));
        in.setCellValueFactory(cellData -> {
            MeterReading meterReading = cellData.getValue();
            String serviceType = meterReading.getService().getDescription();
            return new SimpleStringProperty(serviceType);
        });
        meterReading.setItems(meterReadings);

        ObservableList<Bill> bills = FXCollections.observableList(billDao.findAll(user.getId()));
        serviceName.setCellValueFactory(cellData -> {
            Bill bill = cellData.getValue();
            String serviceName = bill.getService().getName();
            return new SimpleStringProperty(serviceName);
        });
        dateBill.setCellValueFactory(new PropertyValueFactory<>("billDate"));
        amountBill.setCellValueFactory(new PropertyValueFactory<>("amount"));
        billStatus.setCellValueFactory(new PropertyValueFactory<>("billStatus"));
        bill.setItems(bills);

    }
}
