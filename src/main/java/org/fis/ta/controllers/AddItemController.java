package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.fis.ta.exceptions.EmptyFieldException;
import org.fis.ta.exceptions.NoFileSelectedException;
import org.fis.ta.exceptions.PriceNotValidException;
import org.fis.ta.services.ItemService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddItemController {
    @FXML
    private Text addItemMessage;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private ChoiceBox<String> categoryField;

    @FXML
    private TextField descriptionField;

    @FXML
    private static ArrayList<String> images = new ArrayList<>();

    @FXML
    public void initialize() {
        categoryField.getItems().addAll( "Cars, motorcycles and boats", "Real estates", "Electronics and appliances", "Sport");
    }

    public void handleFileChooser() {
        try{
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png"));
            List<File> f = fc.showOpenMultipleDialog(null);
            if(!(f == null)){
                for (File file : f) {
                    images.add(file.getAbsolutePath());
                }
            }
            else{
                throw new NoFileSelectedException();
            }

        }catch (NoFileSelectedException e){
            addItemMessage.setText(e.getMessage());
        }

    }



    @FXML
    public void handleAddAction(){
        try {
            ItemService.addItem(LoginController.getUsername(), nameField.getText(), categoryField.getValue(), descriptionField.getText(), images, priceField.getText());
            addItemMessage.setText("Item added successfully!");

        }catch (PriceNotValidException | EmptyFieldException | NoFileSelectedException e){
            addItemMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackAction(){
        try {
            Stage stage =(Stage) addItemMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("homepage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 600, 600);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
            stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setImage(String image){ images.add(image); } // used for testing
}