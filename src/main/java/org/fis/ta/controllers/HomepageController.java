package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController {

    @FXML
    private Label greetingLabel;

    @FXML
    void handleLogoutAction() {

    }

    public void loadMessage(String username){
        greetingLabel.setText("Hello "+ username);
    }
}

