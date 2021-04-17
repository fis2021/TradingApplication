package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.fis.ta.exceptions.UsernameAlreadyExistsException;
import org.fis.ta.services.UserService;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;



    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());
            registrationMessage.setText("Account created successfully!");


        }catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
