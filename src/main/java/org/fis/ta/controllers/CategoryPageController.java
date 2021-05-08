package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoryPageController {

    private String category;

    @FXML
    private Text categoryMessage;


    public void setCategory(String category) { this.category = category; }
    public String getCategory(){ return category; }

    public void handleSportAction(ActionEvent actionEvent) {
        setCategory("Sport");
        try {
            Stage stage =(Stage) categoryMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("displayItemsPage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 380, 275);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleElectronicsAction(ActionEvent actionEvent) {
        setCategory("Electronics and appliances");
        try {
            Stage stage =(Stage) categoryMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("displayItemsPage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 380, 275);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleEstateAction(ActionEvent actionEvent) {
        setCategory("Real estates");
        try {
            Stage stage =(Stage) categoryMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("displayItemsPage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 380, 275);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAutoAction(ActionEvent actionEvent) {
        setCategory("Cars, motorcycles and boats");
        try {
            Stage stage =(Stage) categoryMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("displayItemsPage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 380, 275);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleBackAction(ActionEvent actionEvent) {
        try {
            Stage stage =(Stage) categoryMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("homepage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 600, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
