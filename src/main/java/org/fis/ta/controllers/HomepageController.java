package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    @FXML
    private Label greetingLabel;

    Parent root;
    Stage stage;
    Scene scene;


    @FXML
    void handleHistoryAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(""));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,600,600);
        stage.setScene(scene);
    }
    @FXML
    void handleCategoriesAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(""));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,600,600);
        stage.setScene(scene);
    }

    @FXML

    void handleAdvertismentsAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addItem.fxml"));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,600,600);
        stage.setScene(scene);
    }

    @FXML
    void handleLogoutAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,380,275);
        stage.setScene(scene);
    }

    public void loadMessage(String username){
        greetingLabel.setText("Hello "+ username);
    }
}

