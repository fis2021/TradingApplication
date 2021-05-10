package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;
import org.fis.ta.services.UserService;

import javax.swing.text.html.ImageView;

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


    public void loadSaleslistPage(){
        username=LoginController.getUsername();

        nameColumn=new TableColumn<>("Name");
        priceColumn=new TableColumn<>("Price");
        nameColumn.setMinWidth(200);
        priceColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(UserService.getCurrentUser(username).getItems());
    }
}
