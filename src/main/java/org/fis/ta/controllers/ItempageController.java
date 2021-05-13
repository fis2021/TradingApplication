package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.fis.ta.MainApp;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;
import org.fis.ta.services.ItemService;
import org.fis.ta.services.UserService;

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

    @FXML
    private Button manageButton;

    @FXML private Text itemnameField;
    @FXML private Text priceField;

    private Item currentItem;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    private Stage manageStage;

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

    @FXML
    void handleManageAction() throws IOException {

        manageStage.showAndWait();
    }

    public void loadManageWindow() throws IOException {
        loader=new FXMLLoader(getClass().getClassLoader().getResource("manageItem.fxml"));
        root=loader.load();
        manageStage=new Stage();
        manageStage.initModality(Modality.APPLICATION_MODAL);
        manageStage.setTitle("Manage item");
        manageStage.setResizable(false);
        manageStage.centerOnScreen();
        scene = new Scene(root,600,400);
        manageStage.setScene(scene);
    }

    void loadItempage(User user, Item item) throws IOException {
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

        if(currentItem.getOwner().equals(LoginController.getUsername()))
            manageButton.setVisible(true);
        else
            manageButton.setVisible(false);

        loadPhoto();
        loadManageWindow();
    }

    void loadPhoto(){
        ID=currentItem.getID();
        itemImage.setImage(new Image("file:" +currentItem.getImages().get(0),523,425,false,false));
        System.out.println(ID);
    }

}

