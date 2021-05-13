package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;

import java.io.IOException;

public class ItempageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
    private Button buyButton;


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

        if(currentItem.getOwner().equals(LoginController.getUsername()))
            buyButton.setVisible(false);
        else
            buyButton.setVisible(true);
    }

    void loadPhoto(){
        ID=currentItem.getID();
        itemImage.setImage(new Image("file:" + currentItem.getImages().get(0),523,425,false,false));
        System.out.println(ID);
    }

    public void handleBuyAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("buyItemPage.fxml"));
            root=loader.load();
            BuyItemController bc = loader.getController();
            bc.loadBuyPage(currentItem);
            stage=(Stage) buyButton.getScene().getWindow();
            scene=new Scene(root,919,643);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

