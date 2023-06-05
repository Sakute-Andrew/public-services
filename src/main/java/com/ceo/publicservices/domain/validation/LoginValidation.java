package com.ceo.publicservices.domain.validation;

import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.infrastructure.dao.UserDao;
import com.ceo.publicservices.infrastructure.dao.impl.UserDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.scene.control.Alert;

import static com.ceo.publicservices.domain.validation.RegistrationValidation.userSingleton;

public class LoginValidation {

    private static UserDao dao = new UserDaoImpl();
    static AlertWindow alertWindow = new AlertWindow();

    public static boolean loginValidate(String username, String password){
        if (username.isEmpty() && password.isEmpty()){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поля не можуть бути порожні");
            return false;
        }
        User user = dao.findByName(username);
        password = PasswordHash.hashedPassword(password);

        if (user == null){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Користувача з таким логіном не існує!");
            return false;
        }

        userSingleton.setUser(user);
        if (user.getUsername().equals(username) && !user.getPasswordHash().equals(password)){
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Неправильний пароль!");
            return false;
        }


            alertWindow.showAlert(Alert.AlertType.CONFIRMATION, "Вітаємо!",
                    "Вітаємо, "+ user.getFirstName()+"!");
        userSingleton.setUser(user);
        return true;
    }



}
