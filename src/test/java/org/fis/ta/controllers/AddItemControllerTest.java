package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.ta.exceptions.*;
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

import static org.testfx.assertions.api.Assertions.assertThat;


@ExtendWith(ApplicationExtension.class)
class AddItemControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-trading-application";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ItemService.initDatabase();
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown(){
        UserService.getUserRepository().close();
        ItemService.getItemRepository().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Trading application");
        primaryStage.setScene(new Scene(root, 380, 275));
        primaryStage.show();
    }

    @Test
    void testAddItem(FxRobot robot) throws PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, EmptyFieldException, PasswordDoesntMatchException {
        UserService.addUser("username","password","password","firstName","lastname","email@mail.com", "+0712312323");

        robot.clickOn("#loginUsername");
        robot.write("username");
        robot.clickOn("#loginPassword");
        robot.write("password");
        robot.clickOn("#loginButton");

        robot.clickOn("#homepageAddButton");

        robot.clickOn("#addItemName");
        robot.write("Item Name");
        robot.clickOn("#addItemPrice");
        robot.write("12.3");

        robot.clickOn("#addItemAddButton");
        assertThat(robot.lookup("#addItemMessage").queryText()).hasText("All fields must be completed!");

        robot.clickOn("#addItemCategory");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#addItemDescription");
        robot.write("Item Description");

        robot.clickOn("#addItemAddButton");
        assertThat(robot.lookup("#addItemMessage").queryText()).hasText("Please enter a valid price!");

        robot.clickOn("#addItemPrice");
        robot.write("55.99");

        robot.clickOn("#addItemAddButton");
        assertThat(robot.lookup("#addItemMessage").queryText()).hasText("The item must have at least 1 image!");

        AddItemController.setImage("Image link");

        robot.clickOn("#addItemAddButton");
        assertThat(robot.lookup("#addItemMessage").queryText()).hasText("Item added successfully!");

    }
}