package org.fis.ta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.ta.model.User;
import org.fis.ta.services.FileSystemService;
import org.fis.ta.services.UserService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import java.io.IOException;

public class MainApp extends Application {

    public void start(Stage primaryStage) throws IOException {
        initDirectory();
        UserService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("itempage.fxml"));
        primaryStage.setTitle("Trading Application");
        primaryStage.setScene(new Scene(root, 919, 643));
        primaryStage.show();
        System.out.println(FileSystemService.APPLICATION_HOME_PATH);

    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
}