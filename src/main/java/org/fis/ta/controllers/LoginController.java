package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis.ta.MainApp;
import org.fis.ta.services.UserService;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Text loginMessage;
    private Stage Stage;

    @FXML
    void handleRegisterAction() throws IOException {
        MainApp m = new MainApp();
        Stage stage = (Stage) passwordField.getScene().getWindow();
        Parent viewRegisterPage = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Scene scene = new Scene(viewRegisterPage,380,275);
        stage.setScene(scene);
    }
    @FXML
    public void handleLoginAction() throws IOException {
        if(UserService.checkLoginCredentials(usernameField.getText(),passwordField.getText())){
            Stage stage = (Stage) passwordField.getScene().getWindow();
            Parent viewHomepageRoot = FXMLLoader.load(getClass().getClassLoader().getResource("homepage.fxml"));
            Scene scene = new Scene(viewHomepageRoot,600,600);
            stage.setScene(scene);
        }
        else
            loginMessage.setText("Account does not exist!");
    }

}
