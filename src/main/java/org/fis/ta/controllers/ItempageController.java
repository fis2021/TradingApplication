package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis.ta.MainApp;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;
import org.fis.ta.services.ItemService;

import java.io.IOException;
import java.util.ArrayList;

public class ItempageController {

    private int ID;
    @FXML
    private ImageView itemImage;

    @FXML
    private TextArea DescriptionArea;

    @FXML
    private Text nameField;

    @FXML
    private Text usernameField;

    @FXML
    private Text emailField;

    @FXML
    private Text phoneField;

    @FXML private Text itemnameField;
    @FXML private Text priceField;

    @FXML
    void handleNextPhotoAction(ActionEvent event) {
        Item item = ItemService.getCurrentItem(ID);
        item.setCounter(item.getCounter()+1);
        System.out.println(item.getCounter());
    }

    @FXML
    void handlePrevPhotoAction() {
        Item item = ItemService.getCurrentItem(ID);
        item.setCounter(item.getCounter()-1);
        System.out.println(item.getCounter());
    }

    @FXML
    void testAction(ActionEvent event) {
        System.out.println("Test");
    }
    void loadItempage(User user, Item item){
        nameField.setText(user.getFirstName() + " " + user.getLastName());
        usernameField.setText("@"+user.getUsername());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhoneNumber());
        itemnameField.setText(item.getName());
        priceField.setText(item.getPrice());
        DescriptionArea.setText(item.getDescription());
        loadPhoto(item);
    }

    void loadPhoto(Item item){
        ID=item.getID();
        itemImage.setImage(new Image("file:" +item.getImages().get(0)));
        System.out.println(ID);
    }

}

