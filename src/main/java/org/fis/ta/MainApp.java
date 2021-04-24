package org.fis.ta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.ta.services.FileSystemService;
import org.fis.ta.services.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import java.io.IOException;

public class MainApp extends Application {

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Trading Application");
        primaryStage.setScene(new Scene(root, 380, 275));
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();


    }
}