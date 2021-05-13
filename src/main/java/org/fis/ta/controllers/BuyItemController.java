package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BuyItemController {

    @FXML
    public Text addItemMessage;

    @FXML
    public ChoiceBox deliveryField;
    @FXML
    public TextField countryField;
    @FXML
    public TextField cityField;
    @FXML
    public TextField streetField;
    @FXML
    public TextField houseNumberField;
    @FXML
    public CheckBox fastDeliveryField;


    @FXML
    public void handleBuyAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleBackAction(ActionEvent actionEvent) {
    }
}
