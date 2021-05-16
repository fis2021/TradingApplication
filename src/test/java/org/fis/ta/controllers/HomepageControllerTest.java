package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({ApplicationExtension.class})
class HomepageControllerTest {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CONFIRMPASSWORD = "password";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String EMAIL = "email@yahoo.com";
    public static final String PHONENUMBER = "+0726223773";

    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".testHomepage";
        FileSystemService.initDirectory();
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root,380,275));
        primaryStage.show();
    }

    @Test
    @DisplayName("Logout button works")
    void testLogoutButton(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageLogout");
    }
    @Test
    @DisplayName("Categories button works")
    void testCategoriesButton(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageCategory");
    }
    @Test
    @DisplayName("Add item button works")
    void testAddItemButton(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageAddButton");
    }
    @Test
    @DisplayName("History button works")
    void testHistoryButton(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageHistory");
    }

    @Test
    void testSaleslistButton(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageSalesList");
        TableView<Item> table = SaleslistController.getThisTable();

    }

}