package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private TableColumn<Item, ImageView> photoColumn;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TableColumn<Item, String> priceColumn;

    @FXML
    private TableColumn<Item, String> dateColumn;
    @FXML
    private TableColumn<Item, String> categoryColumn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML

    public void handleHomepageAction() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("homepage.fxml"));
        root=loader.load();
        HomepageController hc = loader.getController();
        hc.loadMessage(username);
        stage = (Stage) table.getScene().getWindow();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
    }
    public void loadSaleslistPage(){
        username=LoginController.getUsername();
        nameColumn.setText("Name");
        priceColumn.setText("Price");
        dateColumn.setText("Added on:");
        categoryColumn.setText("Category");
        photoColumn.setText("");
        nameColumn.setMinWidth(210);
        priceColumn.setMinWidth(210);
        dateColumn.setMinWidth(210);
        categoryColumn.setMinWidth(210);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.setItems(UserService.getCurrentUser(username).getItems());
    }
}
