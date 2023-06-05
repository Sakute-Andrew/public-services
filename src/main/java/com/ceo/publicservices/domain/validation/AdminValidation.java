package com.ceo.publicservices.domain.validation;

import com.ceo.publicservices.domain.enteties.Admin;
import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.infrastructure.dao.AdminDao;
import com.ceo.publicservices.infrastructure.dao.UserDao;
import com.ceo.publicservices.infrastructure.dao.impl.AdminDaoImpl;
import com.ceo.publicservices.infrastructure.dao.impl.UserDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.scene.control.Alert;

import static com.ceo.publicservices.domain.validation.RegistrationValidation.userSingleton;



public class AdminValidation {

    private static AdminDao dao = new AdminDaoImpl();
    static AlertWindow alertWindow = new AlertWindow();

    public static boolean loginValidate(String username, String password){
        if (username.isEmpty() && password.isEmpty()){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поля не можуть бути порожні");
            return false;
        }
        Admin admin = dao.findByName(username);
        password = PasswordHash.hashedPassword(password);
        if (admin == null){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Адміністратора з таким логіном не існує!");
            return false;
        }
        if (admin.getUsername().equals(username) && !admin.getPassword().equals(password)){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Неправильний пароль!");
            return false;
        }else {
            alertWindow.showAlert(Alert.AlertType.CONFIRMATION, "Вітаємо!",
                    "Вітаємо, " + admin.getUsername() + "!");
            return true;
        }
    }
}
