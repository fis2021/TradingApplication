package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.ta.services.FileSystemService;
import org.fis.ta.services.ItemService;
import org.fis.ta.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class CategoryPageControllerTest {
    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test-trading-application";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        ItemService.initDatabase();
    }

    @AfterEach
    void tearDown(){
        UserService.getDataBase().close();
        ItemService.getDataBase().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("categoryPage.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root,600,500));
        primaryStage.show();
    }

    @Test
    @DisplayName("Testing all buttons from category page.")
    void testButtons(FxRobot robot){
        robot.clickOn("#categoryBackButton");
        robot.clickOn("#homepageCategory");

        robot.clickOn("#categoryAutoButton");
        robot.clickOn("#displayItemBackButton");

        robot.clickOn("#categoryEstateButton");
        robot.clickOn("#displayItemBackButton");

        robot.clickOn("#categorySportButton");
        robot.clickOn("#displayItemBackButton");

        robot.clickOn("#categoryElectronicsButton");
        robot.clickOn("#displayItemBackButton");
    }



}