package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;
import org.fis.ta.services.ItemService;

import java.util.ArrayList;

public class ItempageController {

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
    private int ID;

    @FXML
    void handleNextPhotoAction() {
        Item item = ItemService.getCurrentItem(ID);
        item.setCounter(item.getCounter()-1);
        itemImage.setImage(item.getImages().get(item.getCounter()));
    }

    @FXML
    void handlePrevPhotoAction() {
        Item item = ItemService.getCurrentItem(ID);
        item.setCounter(item.getCounter()-1);
        itemImage.setImage(item.getImages().get(item.getCounter()));
    }

    void loadItempage(User user, Item item){
        nameField.setText(user.getFirstName() + " " + user.getLastName());
        usernameField.setText("@"+user.getUsername());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhoneNumber());
        itemnameField.setText(item.getName());
        priceField.setText(item.getPrice());
        DescriptionArea.setText(item.getDescription());
        ID=item.getID();
    }

}

