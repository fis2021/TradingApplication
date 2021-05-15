package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.fis.ta.services.FileSystemService;
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
class RegistrationControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-trading-application";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown(){
        UserService.getUserRepository().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Trading application");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    @Test
    void testRegistration(FxRobot robot) {
        robot.clickOn("#registrationUsername");
        robot.write("username");
        robot.clickOn("#registrationPassword");
        robot.write("password");

        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("All fields must be completed!");

        robot.clickOn("#registrationConfirmPassword");
        robot.write("pass");

        robot.clickOn("#registrationFirstName");
        robot.write("firstName");
        robot.clickOn("#registrationLastName");
        robot.write("lastName");
        robot.clickOn("#registrationEmail");
        robot.write("email");
        robot.clickOn("#registrationPhoneNumber");
        robot.write("+4563");

        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Password and Confirm Password do not match!");

        robot.clickOn("#registrationConfirmPassword");
        robot.write("word");

        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("This is not a valid email address!");

        robot.clickOn("#registrationEmail");
        robot.write("@mail");

        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("This is not a valid email address!");

        robot.clickOn("#registrationEmail");
        robot.write(".com");

        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please enter a valid phone number!");

        robot.clickOn("#registrationPhoneNumber");
        robot.write("021312");

        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        Assertions.assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#registrationRegisterButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(String.format(String.format("An account with the username %s already exists!", "username")));

        robot.clickOn("#registrationUsername");
        robot.write("1");
        robot.clickOn("#registrationRegisterButton");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        Assertions.assertThat(UserService.getAllUsers()).size().isEqualTo(2);

        robot.clickOn("#registrationBackButton");
    }

}