package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis.ta.exceptions.*;
import org.fis.ta.services.UserService;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {


    @FXML
    private Text registrationMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    public RegistrationController() {
    }


    @FXML
    public void handleLoginAction(){
        try {
            /*MainApp m = new MainApp();
            m.changeScene("login.fxml");*/
            Stage stage =(Stage) registrationMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 380, 275);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRegisterAction() {
        try {

            UserService.addUser(usernameField.getText(), passwordField.getText(), confirmPasswordField.getText(), firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneNumberField.getText());
            registrationMessage.setText("Account created successfully!");

        }catch (UsernameAlreadyExistsException | PasswordDoesntMatchException | EmailNotValidException | EmptyFieldException | PhoneNumberNotValidException e){
            registrationMessage.setText(e.getMessage());
        }
    }

}
