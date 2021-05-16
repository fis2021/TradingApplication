package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
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
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({ApplicationExtension.class})

class SaleslistControllerTest {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CONFIRMPASSWORD = "password";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String EMAIL = "email@yahoo.com";
    public static final String PHONENUMBER = "+0726223773";
    public static final ArrayList<String> IMAGES = ItemService.getFakeList();

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root,380,275));
        primaryStage.show();
    }

    @Test
    @DisplayName("Homepage button works!")
    void testHomepageButton(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageSalesList");
        robot.clickOn("#saleslistHomepageButton");
    }

    @Test
    @DisplayName("Homepage button works!")
    void testGoToItempage(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException, PriceNotValidException, NoFileSelectedException, InterruptedException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        ItemService.addItem(USERNAME,"nume","Categorie","Descriere",IMAGES,"120.00");
        Item item = ItemService.getAllItems().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageSalesList");
        robot.type(KeyCode.UP);
        robot.clickOn("#saleslistTable");
    }

    @Test
    @DisplayName("Salelist page loads correctly")
    void testLoadPage(FxRobot robot) throws PhoneNumberNotValidException, EmptyFieldException, EmailNotValidException, PasswordDoesntMatchException, UsernameAlreadyExistsException, PriceNotValidException, NoFileSelectedException {
        UserService.addUser(USERNAME,PASSWORD,CONFIRMPASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER);
        User user = UserService.getAllUsers().get(0);
        ItemService.addItem(USERNAME,"nume","Categorie","Descriere",IMAGES,"120.00");
        Item item = ItemService.getAllItems().get(0);

        robot.clickOn("#loginUsername");
        robot.write(USERNAME);
        robot.clickOn("#loginPassword");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#homepageSalesList");
        TableView<Item> table = SaleslistController.getThisTable();
        assertThat(table.getColumns().get(0).getCellObservableValue(0).getValue()).isEqualTo(USERNAME);
        assertThat(table.getColumns().get(1).getCellObservableValue(0).getValue()).isEqualTo("nume");
        assertThat(table.getColumns().get(2).getCellObservableValue(0).getValue()).isEqualTo("120.00");
        assertThat(table.getColumns().get(3).getCellObservableValue(0).getValue()).isEqualTo("Categorie");
        assertThat(table.getColumns().get(4).getCellObservableValue(0).getValue()).isEqualTo(Item.getThisDate());
    }


}