package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.ta.services.FileSystemService;
import org.fis.ta.services.ItemService;
import org.fis.ta.services.UserService;
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

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Trading application");
        primaryStage.setScene(new Scene(root, 380, 275));
        primaryStage.show();
    }

    @Test
    void testAddItem(FxRobot robot){
        robot.clickOn("#loginRegisterButton");

        robot.clickOn("#registrationUsername");
        robot.write("username");
        robot.clickOn("#registrationPassword");
        robot.write("password");
        robot.clickOn("#registrationConfirmPassword");
        robot.write("password");
        robot.clickOn("#registrationFirstName");
        robot.write("firstName");
        robot.clickOn("#registrationLastName");
        robot.write("lastName");
        robot.clickOn("#registrationEmail");
        robot.write("email@mail.com");
        robot.clickOn("#registrationPhoneNumber");
        robot.write("+072131241");
        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        robot.clickOn("#registrationBackButton");

        robot.clickOn("#loginUsername");
        robot.write("username");
        robot.clickOn("#loginPassword");
        robot.write("password");
        robot.clickOn("#loginButton");

        robot.clickOn("#homepageAddButton");

        robot.clickOn("addItemName");
        robot.write("Item Name");
        robot.clickOn("addItemPrice");
        robot.write("12.34");

        robot.clickOn("#addItemAddButton");
        assertThat(robot.lookup("#addItemMessage").queryText()).hasText("Account created successfully!");



    }
}