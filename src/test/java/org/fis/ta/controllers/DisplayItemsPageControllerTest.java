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

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class DisplayItemsPageControllerTest {
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("categoryPage.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root,600,500));
        primaryStage.show();
    }

    @Test
    @DisplayName("Back button works from every page!")
    void testBackButton(FxRobot robot){
        robot.clickOn("#categoryAutoButton");
        robot.clickOn("#displayItemBackButton");

        robot.clickOn("#categoryEstateButton");
        robot.clickOn("#displayItemBackButton");

        robot.clickOn("#categorySportButton");
        robot.clickOn("#displayItemBackButton");

        robot.clickOn("#categoryElectronicsButton");
        robot.clickOn("#displayItemBackButton");
    }

    @Test
    @DisplayName("Sport page loads correctly and display the right items")
    void testSportPage(FxRobot robot) throws PriceNotValidException, NoFileSelectedException, EmptyFieldException, PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, PasswordDoesntMatchException {
        UserService.addUser("username","password","password","firstname","lastName","mail@mail.com","+073231312");
        IMAGES.add("image");
        ItemService.addItem("username","Bicycle","Sport","Description",IMAGES,"120.00");
        ItemService.addItem("username","Car","Cars, motorcycles and boats","Description",IMAGES,"5.300.00");
        ItemService.addItem("username","Apartment","Estate","Description",IMAGES,"50.000.00");
        ItemService.addItem("username","Phone","Electronics and appliances","Samsung",IMAGES,"1.200.00");

        robot.clickOn("#categorySportButton");

        TableView<Item> table = DisplayItemsPageController.getTable();

        assertThat(table.getColumns().get(0).getCellObservableValue(0).getValue()).isEqualTo("username");
        assertThat(table.getColumns().get(1).getCellObservableValue(0).getValue()).isEqualTo("Bicycle");
        assertThat(table.getColumns().get(2).getCellObservableValue(0).getValue()).isEqualTo("120.00");
        assertThat(table.getColumns().get(3).getCellObservableValue(0).getValue()).isEqualTo(Item.getThisDate());

        assertThat(table.getColumns().get(0).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(1).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(2).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(3).getCellObservableValue(1)).isNull();
    }

    @Test
    @DisplayName("Auto page loads correctly and display the right items")
    void testAutoPage(FxRobot robot) throws PriceNotValidException, NoFileSelectedException, EmptyFieldException, PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, PasswordDoesntMatchException {
        UserService.addUser("username","password","password","firstname","lastName","mail@mail.com","+073231312");
        IMAGES.add("image");
        ItemService.addItem("username","Bicycle","Sport","Description",IMAGES,"120.00");
        ItemService.addItem("username","Car","Cars, motorcycles and boats","Description",IMAGES,"5.300.00");
        ItemService.addItem("username","Apartment","Estate","Description",IMAGES,"50.000.00");
        ItemService.addItem("username","Phone","Electronics and appliances","Samsung",IMAGES,"1.200.00");

        robot.clickOn("#categoryAutoButton");

        TableView<Item> table = DisplayItemsPageController.getTable();

        assertThat(table.getColumns().get(0).getCellObservableValue(0).getValue()).isEqualTo("username");
        assertThat(table.getColumns().get(1).getCellObservableValue(0).getValue()).isEqualTo("Car");
        assertThat(table.getColumns().get(2).getCellObservableValue(0).getValue()).isEqualTo("5.300.00");
        assertThat(table.getColumns().get(3).getCellObservableValue(0).getValue()).isEqualTo(Item.getThisDate());

        assertThat(table.getColumns().get(0).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(1).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(2).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(3).getCellObservableValue(1)).isNull();
    }

    @Test
    @DisplayName("Estate page loads correctly and display the right items")
    void testEstatePage(FxRobot robot) throws PriceNotValidException, NoFileSelectedException, EmptyFieldException, PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, PasswordDoesntMatchException {
        UserService.addUser("username","password","password","firstname","lastName","mail@mail.com","+073231312");
        IMAGES.add("image");
        ItemService.addItem("username","Bicycle","Sport","Description",IMAGES,"120.00");
        ItemService.addItem("username","Car","Cars, motorcycles and boats","Description",IMAGES,"5.300.00");
        ItemService.addItem("username","Apartment","Real estates","Description",IMAGES,"50.000.00");
        ItemService.addItem("username","Phone","Electronics and appliances","Samsung",IMAGES,"1.200.00");

        robot.clickOn("#categoryEstateButton");

        TableView<Item> table = DisplayItemsPageController.getTable();

        assertThat(table.getColumns().get(0).getCellObservableValue(0).getValue()).isEqualTo("username");
        assertThat(table.getColumns().get(1).getCellObservableValue(0).getValue()).isEqualTo("Apartment");
        assertThat(table.getColumns().get(2).getCellObservableValue(0).getValue()).isEqualTo("50.000.00");
        assertThat(table.getColumns().get(3).getCellObservableValue(0).getValue()).isEqualTo(Item.getThisDate());

        assertThat(table.getColumns().get(0).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(1).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(2).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(3).getCellObservableValue(1)).isNull();
    }

    @Test
    @DisplayName("Electronics page loads correctly and display the right items")
    void testElectronicsPage(FxRobot robot) throws PriceNotValidException, NoFileSelectedException, EmptyFieldException, PhoneNumberNotValidException, UsernameAlreadyExistsException, EmailNotValidException, PasswordDoesntMatchException {
        UserService.addUser("username","password","password","firstname","lastName","mail@mail.com","+073231312");
        IMAGES.add("image");
        ItemService.addItem("username","Bicycle","Sport","Description",IMAGES,"120.00");
        ItemService.addItem("username","Car","Cars, motorcycles and boats","Description",IMAGES,"5.300.00");
        ItemService.addItem("username","Apartment","Estate","Description",IMAGES,"50.000.00");
        ItemService.addItem("username","Phone","Electronics and appliances","Samsung",IMAGES,"1.200.00");

        robot.clickOn("#categoryElectronicsButton");

        TableView<Item> table = DisplayItemsPageController.getTable();

        assertThat(table.getColumns().get(0).getCellObservableValue(0).getValue()).isEqualTo("username");
        assertThat(table.getColumns().get(1).getCellObservableValue(0).getValue()).isEqualTo("Phone");
        assertThat(table.getColumns().get(2).getCellObservableValue(0).getValue()).isEqualTo("1.200.00");
        assertThat(table.getColumns().get(3).getCellObservableValue(0).getValue()).isEqualTo(Item.getThisDate());

        assertThat(table.getColumns().get(0).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(1).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(2).getCellObservableValue(1)).isNull();
        assertThat(table.getColumns().get(3).getCellObservableValue(1)).isNull();
    }


}