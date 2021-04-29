package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis.ta.exceptions.PriceNotValidException;
import org.fis.ta.services.ItemService;

import java.io.IOException;

public class AddItemController {
    @FXML
    private Text addItemMessage;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField categoryField;

    @FXML
    public void handleAddAction(){
        try {
            ItemService.addItem(LoginController.getUsername() ,nameField.getText(), priceField.getText(), categoryField.getText());
            addItemMessage.setText("Item added successfully!");

        }catch (PriceNotValidException e){
            addItemMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackAction(){
        try {
            Stage stage =(Stage) addItemMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("homepage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 380, 275);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

