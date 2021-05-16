package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.ta.exceptions.*;
import org.fis.ta.model.User;
import org.fis.ta.services.FileSystemService;
import org.fis.ta.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith({ApplicationExtension.class})
class LoginTest {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CONFIRMPASSWORD = "password";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String EMAIL = "email@yahoo.com";
    public static final String PHONENUMBER = "+0726223773";
    @BeforeEach
    void setUp() throws IOException {
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root,380,275));
        primaryStage.show();
    }

    @Test
    void testLogin(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
    }

    @Test
    void testIncorrectUsername(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME+"1");
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Username or password do not exist!");
    }
    @Test
    void testIncorrectPassword(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD+"1");
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Username or password do not exist!");
    }

    @Test
    void testEmptyUsername(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD+"1");
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("All fields must be completed!");
    }

    @Test
    void testEmptyPassword(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("All fields must be completed!");
    }

    @Test
    void testRegisterButton(FxRobot robot){
        robot.clickOn("#loginRegisterButton");
    }

}