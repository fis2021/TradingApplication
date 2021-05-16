package org.fis.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.ta.exceptions.*;
import org.fis.ta.model.Item;
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

@ExtendWith(ApplicationExtension.class)
class PersonalItemListControllerTest {
    public static final ArrayList<String> IMAGES = new ArrayList<>();

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
    @DisplayName("Testing if page loads and display items.")
    void testBackButton(FxRobot robot) throws PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, EmptyFieldException, PasswordDoesntMatchException, PriceNotValidException, NoFileSelectedException {
        UserService.addUser("username","password","password","firstname","lastName","mail@mail.com","+073231312");
        UserService.addUser("username2","password","password","firstname","lastName","mail@mail.com","+073231312");
        IMAGES.add("image");
        ItemService.addItem("username","Bicycle","Sport","Description",IMAGES,"120.00");
        ItemService.addItem("username2","Car","Cars, motorcycles and boats","Description",IMAGES,"5.300.00");
        ItemService.addItem("username2","Apartment","Estate","Description",IMAGES,"50.000.00");
        ItemService.addItem("username","Phone","Electronics and appliances","Samsung",IMAGES,"1.200.00");

        robot.clickOn("#loginUsername");
        robot.write("username");

        robot.clickOn("#loginPassword");
        robot.write("password");

        robot.clickOn("#loginButton");

        robot.clickOn("#homepageHistory");

        TableView<Item> table = PersonalItemListController.getTable();

        assertThat(table.getColumns().get(0).getCellObservableValue(0).getValue()).isEqualTo("Sport");
        assertThat(table.getColumns().get(1).getCellObservableValue(0).getValue()).isEqualTo("Bicycle");
        assertThat(table.getColumns().get(2).getCellObservableValue(0).getValue()).isEqualTo("120.00");
        assertThat(table.getColumns().get(3).getCellObservableValue(0).getValue()).isEqualTo(Item.getThisDate());

        assertThat(table.getColumns().get(0).getCellObservableValue(1).getValue()).isEqualTo("Electronics and appliances");
        assertThat(table.getColumns().get(1).getCellObservableValue(1).getValue()).isEqualTo("Phone");
        assertThat(table.getColumns().get(2).getCellObservableValue(1).getValue()).isEqualTo("1.200.00");
        assertThat(table.getColumns().get(3).getCellObservableValue(1).getValue()).isEqualTo(Item.getThisDate());

        assertThat(table.getColumns().get(0).getCellObservableValue(2)).isNull();
        assertThat(table.getColumns().get(1).getCellObservableValue(2)).isNull();
        assertThat(table.getColumns().get(2).getCellObservableValue(2)).isNull();
        assertThat(table.getColumns().get(3).getCellObservableValue(2)).isNull();
    }
}