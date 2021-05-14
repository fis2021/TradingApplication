package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;
import org.fis.ta.services.UserService;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SaleslistController {

    private String username;
    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, ImageView> ownerColumn;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TableColumn<Item, String> priceColumn;

    @FXML
    private TableColumn<Item, String> dateColumn;
    @FXML
    private TableColumn<Item, String> categoryColumn;
    private static final ObjectRepository<User> users = UserService.getUserRepository();

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Stage thisStage;

    public static Stage getThisStage()
    {
        return thisStage;
    }

    @FXML
    public void handleHomepageAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("homepage.fxml"));
        root = loader.load();
        HomepageController hc = loader.getController();
        hc.loadMessage(username);
        stage = (Stage) table.getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        ItempageController.setLastScene("saleslist");
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
    }

    @FXML

    public void goToItempage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("itempage.fxml"));
        root=loader.load();
        ItempageController ic = loader.getController();
        if(table.getSelectionModel().getSelectedItem()!=null) {
            ic.loadItempage(UserService.getCurrentUser(username), table.getSelectionModel().getSelectedItem());
            stage = (Stage) table.getScene().getWindow();
            scene = new Scene(root, 919, 643);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        }
        thisStage=stage;
    }

    public void loadSaleslistPage() {
        username = LoginController.getUsername();
        nameColumn.setText("Name");
        priceColumn.setText("Price");
        dateColumn.setText("Added on:");
        categoryColumn.setText("Category");
        ownerColumn.setText("Seller");
        ownerColumn.setMinWidth(200);
        nameColumn.setMinWidth(200);
        priceColumn.setMinWidth(200);
        dateColumn.setMinWidth(200);
        categoryColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        table.setItems(UserService.getCurrentUser(username).getItems());
    }
}