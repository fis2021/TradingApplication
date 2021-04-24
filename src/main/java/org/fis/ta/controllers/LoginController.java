package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.fis.ta.MainApp;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    private Stage Stage;

    @FXML
    void handleRegisterAction() throws IOException {
        MainApp m = new MainApp();
        Stage stage = (Stage) passwordField.getScene().getWindow();
        Parent viewRegisterPage = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Scene scene = new Scene(viewRegisterPage,380,275);
        stage.setScene(scene);
    }

}
