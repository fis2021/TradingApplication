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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith({ApplicationExtension.class})
class ManageItemTest {


    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CONFIRMPASSWORD = "password";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String EMAIL = "email@yahoo.com";
    public static final String PHONENUMBER = "+0726223773";
    public static final String OWNER = "owner";
    public static final String NAME = "username";
    public static final String CATEGORY = "category";
    public static final String DESCRIPTION = "description";
    public static final ArrayList<String> IMAGES = ItemService.getFakeList();
    public static final String PRICE = "120.00";
    public static final String DATE = "date";
    public static final String NEWOWNER = "new owner";
    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test-trading-application";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        ItemService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.getDataBase().close();
        ItemService.getDataBase().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root,380,275));
        primaryStage.show();
    }

    @Test
    @DisplayName("Manage button works properly!")
    void testManageButton(FxRobot robot) throws EmptyFieldException, NoFileSelectedException, PriceNotValidException, PhoneNumberNotValidException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException, IOException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        ItemService.addItem(USERNAME,NAME,CATEGORY,DESCRIPTION,IMAGES,PRICE);
        Item item = ItemService.getAllItems().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");


        robot.clickOn("#homepageSalesList");
        robot.type(KeyCode.UP);
        robot.clickOn("#saleslistTable");
        robot.clickOn("#itempageManageButton");
    }

    @Test
    @DisplayName("Edit button works properly!")
    void testEditButton(FxRobot robot) throws EmptyFieldException, NoFileSelectedException, PriceNotValidException, PhoneNumberNotValidException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException, IOException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        ItemService.addItem(USERNAME,NAME,CATEGORY,DESCRIPTION,IMAGES,PRICE);
        Item item = ItemService.getAllItems().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");


        robot.clickOn("#homepageSalesList");
        robot.type(KeyCode.UP);
        robot.clickOn("#saleslistTable");
        robot.clickOn("#itempageManageButton");
        robot.clickOn("#managepageEditButton");
    }

    @Test
    @DisplayName("Delete button works properly!")
    void testDeleteButton(FxRobot robot) throws EmptyFieldException, NoFileSelectedException, PriceNotValidException, PhoneNumberNotValidException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException, IOException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        ItemService.addItem(USERNAME,NAME,CATEGORY,DESCRIPTION,IMAGES,PRICE);
        Item item = ItemService.getAllItems().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");


        robot.clickOn("#homepageSalesList");
        robot.type(KeyCode.UP);
        robot.clickOn("#saleslistTable");
        robot.clickOn("#itempageManageButton");
        robot.clickOn("#managepageDeleteButton");
    }
    @Test
    @DisplayName("Item updates in the database")
    void testEditItem(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException, PriceNotValidException, NoFileSelectedException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        ItemService.addItem(USERNAME,NAME,CATEGORY,DESCRIPTION,IMAGES,PRICE);
        Item item = ItemService.getAllItems().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");


        robot.clickOn("#homepageSalesList");
        robot.type(KeyCode.UP);
        robot.clickOn("#saleslistTable");
        robot.clickOn("#itempageManageButton");
        robot.clickOn("#managepageEditButton");

        robot.clickOn("#editpagePriceField");
        robot.write("120");

        robot.clickOn("#editpageEditButton");
        assertThat(robot.lookup("#editpageErrorMessage").queryText()).hasText("Please enter a valid price!");


        robot.clickOn("#editpagePriceField");
        robot.write(".00");
        robot.clickOn("#editpageDescriptionField");
        robot.write("1");
        robot.clickOn("#editpageEditButton");
    }

    @Test
    @DisplayName("Item deletes from the database")
    void testDeleteItem(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException, PriceNotValidException, NoFileSelectedException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        ItemService.addItem(USERNAME,NAME,CATEGORY,DESCRIPTION,IMAGES,PRICE);
        Item item = ItemService.getAllItems().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");


        robot.clickOn("#homepageSalesList");
        robot.type(KeyCode.UP);
        robot.clickOn("#saleslistTable");
        robot.clickOn("#itempageManageButton");
        robot.clickOn("#managepageDeleteButton");
        robot.clickOn("#noButton");
        assertThat(ItemService.getAllItems()).size().isEqualTo(1);
        robot.clickOn("#itempageManageButton");
        robot.clickOn("#managepageDeleteButton");
        robot.clickOn("#yesButton");
        assertThat(ItemService.getAllItems()).isEmpty();

    }
}


