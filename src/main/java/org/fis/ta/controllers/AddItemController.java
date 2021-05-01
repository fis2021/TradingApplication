package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fis.ta.exceptions.EmptyFieldException;
import org.fis.ta.exceptions.PriceNotValidException;
import org.fis.ta.services.ItemService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    private TextField descriptionField;

    @FXML
    private ArrayList<Image> images;
    public void handleFileChooser() {
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png"));
            ArrayList<File> f = (ArrayList<File>) fc.showOpenMultipleDialog(null);
            for (File file : f) {
                FileInputStream input = new FileInputStream(file.getAbsolutePath());
                images.add(new Image(input));
            }
        }catch (FileNotFoundException e)
        {
            addItemMessage.setText(e.getMessage());
        }
    }



    @FXML
    public void handleAddAction(){
        try {
            ItemService.addItem(LoginController.getUsername(), nameField.getText(), categoryField.getText(), descriptionField.getText(), images, priceField.getText());
            addItemMessage.setText("Item added successfully!");

        }catch (PriceNotValidException | EmptyFieldException e){
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