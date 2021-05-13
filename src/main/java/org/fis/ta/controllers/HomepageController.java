package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    @FXML
    private Label greetingLabel;

    Parent root;
    Stage stage;
    Scene scene;

    public Label getLabel()
    {
        return greetingLabel;
    }


    @FXML
    void handleHistoryAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(""));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,600,600);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
    }
    @FXML
    void handleCategoriesAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("categoryPage.fxml"));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,600,600);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
    }

    @FXML

    void handleAddItemAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addItem.fxml"));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,600,600);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
    }


    @FXML
    void handleSaleslistAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("saleslist.fxml"));
        root=loader.load();
        SaleslistController sc = loader.getController();
        sc.loadSaleslistPage();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,919,643);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);

    }

    @FXML
    void handleLogoutAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        root=loader.load();
        stage=(Stage) greetingLabel.getScene().getWindow();
        scene=new Scene(root,380,275);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
    }

    public void loadMessage(String username){
        greetingLabel.setText("Hello "+ username);
    }
}

