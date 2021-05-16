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
    void testBackButton(FxRobot robot){
        robot.clickOn("#categoryBackButton");
        robot.clickOn("#homepageCategory");
    }

    @Test
    void testAutoButton(FxRobot robot){
        robot.clickOn("#categoryAutoButton");
        robot.clickOn("#displayItemBackButton");
    }

    @Test
    void testEstateButton(FxRobot robot){
        robot.clickOn("#categoryEstateButton");
        robot.clickOn("#displayItemBackButton");
    }

    @Test
    void testSportButton(FxRobot robot){
        robot.clickOn("#categorySportButton");
        robot.clickOn("#displayItemBackButton");
    }

    @Test
    void testElectronicsButton(FxRobot robot){
        robot.clickOn("#categoryElectronicsButton");
        robot.clickOn("#displayItemBackButton");
    }

}