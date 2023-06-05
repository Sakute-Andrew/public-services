package com.ceo.publicservices.domain.validation;

import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.domain.enteties.UserSingleton;
import com.ceo.publicservices.infrastructure.dao.UserDao;
import com.ceo.publicservices.infrastructure.dao.impl.UserDaoImpl;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidation {


    static UserSingleton userSingleton = UserSingleton.getInstance();
    private static UserDao dao = new UserDaoImpl();

    static AlertWindow alertWindow = new AlertWindow();

    public static boolean registerValidate(String username, String password, String repeatPassword, String firstName, String lastName) {
        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || firstName.isEmpty() || repeatPassword == null || firstName.isEmpty()) {
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поля не можуть бути порожні!");
            return false;
        }
        if (username.length() > 40 || password.length() > 40 || repeatPassword.length() > 40 || firstName.length() > 40) {
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поля не можуть бути довшими, аніж за 40 символів!");
            return false;
        }
        if (username.length() < 3 || password.length() < 9  || repeatPassword.length() < 9  || firstName.length() < 3 ) {
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Поля не можуть бути менжі за 3 символи, а паролі за 9");
            return false;
        }
        if (!password.equals(repeatPassword)) {
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Паролі не збігаються");
            return false;
        }
        if (!isPasswordValid(password)) {
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Помилка реєстрації! Поля мають містити настуне: \n" +
                            "-мати хоч 1 цифр '0-9' \n" +
                            "-складатись тільки з латинських літер \n" +
                            "-мати спецсимволи: '@#$%^&+=' \n" +
                            "-мати хоч 1 букву верхнього регістру");
            return false;
        }
        if (dao.findByName(username) != null) {
            alertWindow.showAlert(Alert.AlertType.ERROR, "Помилка!",
                    "Користувач з таким логіном вже існує!");
            return false;
        }
        User userRegister = new User();
        userRegister.setUsername(username);
        userRegister.setFirstName(firstName);
        userRegister.setLastName(lastName);
        userRegister.setPasswordHash(PasswordHash.hashedPassword(password));
        userRegister.setAccountNumber(RandomNumberGenerator.getAccountNumber());
        userSingleton.setUser(userRegister);
        dao.save(userRegister);
        alertWindow.showAlert(Alert.AlertType.INFORMATION, "Вітаємо!",
                "Вітаємо, " + firstName + "!");
        return true;

    }

    private static boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{9,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
