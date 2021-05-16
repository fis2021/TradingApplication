package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.ta.exceptions.*;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;
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

import java.util.ArrayList;

import static org.testfx.assertions.api.Assertions.assertThat;


@ExtendWith(ApplicationExtension.class)
class BuyItemControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-trading-application";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown(){
        UserService.getDataBase().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        ArrayList images = new ArrayList();
        images.add("image1");
        images.add("image2");
        ItemService.initDatabase();
        ItemService.addItem("username", "Bicycle", "Sport", "New", images, "1.345.99");
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("buyItemPage.fxml"));
        Parent root = loader.load();
        BuyItemController.loadBuyPage(new Item("username", "Bicycle", "Sport", "New", images, "1.345.99", "23/10/2020"));
        primaryStage.setTitle("Trading application");
        primaryStage.setScene(new Scene(root, 380, 275));
        primaryStage.show();
    }


    @Test
    void testAddItem(FxRobot robot) throws PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, EmptyFieldException, PasswordDoesntMatchException {
        UserService.addUser("username","password","password","firstName","lastname","email@mail.com", "+0712312323");
        LoginController.setUsername("username");

        robot.clickOn("#buyItemDelivery");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER)
        ;
        //robot.clickOn("#buyItemFastDelivery");


        robot.clickOn("#buyItemCountry");
        robot.write("Romania");

        robot.clickOn("#buyItemCity");
        robot.write("Timisoara");

        robot.clickOn("#buyItemStreet");
        robot.write("Carpati");

        robot.clickOn("#buyItemButton");
        assertThat(robot.lookup("#buyItemMessage").queryText()).hasText("All fields must be completed!");

        robot.clickOn("#buyItemNumber");
        robot.write("28");

        robot.clickOn("#buyItemButton");
        assertThat(robot.lookup("#buyItemMessage").queryText()).hasText("Item will arrive to you in 7days after it's current owner send it");

    }
}