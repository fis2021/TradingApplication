package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.fis.ta.model.Item;

import java.io.IOException;

public class ManageItemController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    @FXML
    private Button button;


    @FXML
    void handleCloseAction(){
        stage=(Stage) button.getScene().getWindow();
        stage.close();
    }
    @FXML
    void handleDeleteAction() throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("deleteItemAlertBox.fxml"));
        root = loader.load();
        scene = new Scene(root,600,400);
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
    }

    @FXML
    void handleEditAction() throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("editItem.fxml"));
        root = loader.load();
        scene = new Scene(root,600,400);
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
        EditItemController eic = loader.getController();
        eic.loadEditFields();
    }


}
