package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    void handleDeleteAction() throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("deleteItemAlertBox.fxml"));
        root = loader.load();
        scene = new Scene(root,600,400);
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void handleEditAction() throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("editItem.fxml"));
        root = loader.load();
        scene = new Scene(root,600,400);
        stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public Item getCurrentItem() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("itempage.fxml"));
        Parent root = loader.load();
        ItempageController ic = loader.getController();
        return ic.getCurrentItem();
    }

}
