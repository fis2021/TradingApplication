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

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDirectory();
        UserService.initDatabase();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
        primaryStage.setTitle("Trading application");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();


    }
    /*
    public void changeScene(String fxml) throws IOException {
        Stage primaryStage = null;
        Parent loginpane =FXMLLoader.load(getClass().getResource(fxml));
        primaryStage.getScene().setRoot(loginpane);

    }*/
}