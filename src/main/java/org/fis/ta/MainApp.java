package org.fis.ta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.fis.ta.services.ItemService;
import org.fis.ta.services.UserService;

import java.io.IOException;

public class MainApp extends Application {

    public void start(Stage primaryStage) throws IOException {
        UserService.initDatabase();
        ItemService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Trading Application");
        primaryStage.setScene(new Scene(root, 380, 275));
        primaryStage.setResizable(false);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((primScreenBounds.getHeight()-primaryStage.getHeight())/2);
    }

}