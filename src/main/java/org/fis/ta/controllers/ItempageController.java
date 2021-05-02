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

    private Item currentItem;


    @FXML
    void handleNextPhotoAction(ActionEvent event) {
        if(currentItem.getCounter()<currentItem.getImages().size()-1) {
            currentItem.setCounter(currentItem.getCounter() + 1);
            itemImage.setImage(new Image("file:" + currentItem.getImages().get(currentItem.getCounter()),523,425,false,false));
        }
    }

    @FXML
    void handlePrevPhotoAction() {
        if(currentItem.getCounter()>0) {
            currentItem.setCounter(currentItem.getCounter() - 1);
            itemImage.setImage(new Image("file:" + currentItem.getImages().get(currentItem.getCounter()),523,425,false,false));
        }
    }

    void loadItempage(User user, Item item){
        nameField.setText(user.getFirstName() + " " + user.getLastName());
        usernameField.setText("@"+user.getUsername());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhoneNumber());
        itemnameField.setText(item.getName());
        priceField.setText(item.getPrice());
        DescriptionArea.setText(item.getDescription());
        DescriptionArea.setEditable(false);
        DescriptionArea.setWrapText(true);
        currentItem=item;
        loadPhoto();
        System.out.println(currentItem.getImages().size());
    }

    void loadPhoto(){
        ID=currentItem.getID();
        itemImage.setImage(new Image("file:" +currentItem.getImages().get(0),523,425,false,false));
        System.out.println(ID);
    }

}

