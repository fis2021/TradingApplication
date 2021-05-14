package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis.ta.exceptions.EmptyFieldException;
import org.fis.ta.model.Item;
import org.fis.ta.services.ItemService;

import java.io.IOException;

public class BuyItemController {

    @FXML
    private Text buyItemMessage;

    @FXML
    private ChoiceBox<String> deliveryField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField houseNumberField;
    @FXML
    private CheckBox fastDeliveryField;
    @FXML
    private Label fastDeliveryText;

    private Item currentItem;

    @FXML
    public void initialize() {
        deliveryField.getItems().addAll( "Courier delivery","Post delivery");
        fastDeliveryField.setVisible(false);
        fastDeliveryText.setVisible(false);
        deliveryField.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue.equals("Courier delivery")) {
                fastDeliveryField.setVisible(true);
                fastDeliveryText.setVisible(true);
            }else{
                fastDeliveryField.setVisible(false);
                fastDeliveryText.setVisible(false);
                fastDeliveryField.setSelected(false);
            }
        });
    }


    @FXML
    public void handleBuyAction(ActionEvent actionEvent){
        try {
            buyItemMessage.setText(ItemService.buyItem(currentItem, deliveryField.getValue(), countryField.getText(), cityField.getText(), streetField.getText(), houseNumberField.getText(), fastDeliveryField.isSelected(), LoginController.getUsername()));
        }catch (EmptyFieldException e){
            buyItemMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackAction(ActionEvent actionEvent) {
        try {
            Stage stage =(Stage) buyItemMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("homepage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 600, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBuyPage(Item item){
        this.currentItem = item;
    }
}
