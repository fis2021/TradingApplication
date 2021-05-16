package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
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

    @FXML
    private Button manageButton;

    @FXML
    private Text itemnameField;
    @FXML
    private Text priceField;

    private static Item thisItem;
    private Item currentItem;
    private FXMLLoader loader;
    private Stage manageStage;
    private static Stage thisStage;



    private static String lastScene;
    public static void setLastScene(String value) { lastScene = value;
    }

    @FXML
    void handleBackAction() throws IOException {
        if (lastScene.equals("saleslist")) {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("saleslist.fxml"));
            root = loader.load();
            SaleslistController sc = loader.getController();
            sc.loadSaleslistPage();
            stage = (Stage) priceField.getScene().getWindow();
            scene = new Scene(root, 919, 643);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        } else {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("displayItemsPage.fxml"));
            root = loader.load();
            stage = (Stage) priceField.getScene().getWindow();
            scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        }
    }

    @FXML
    private Button buyButton;


    @FXML
    void handleNextPhotoAction(ActionEvent event) {
        if (currentItem.getCounter() < currentItem.getImages().size() - 1) {
            currentItem.setCounter(currentItem.getCounter() + 1);
            itemImage.setImage(new Image("file:" + currentItem.getImages().get(currentItem.getCounter()), 523, 425, false, false));
        }
    }

    @FXML
    void handlePrevPhotoAction() {
        if (currentItem.getCounter() > 0) {
            currentItem.setCounter(currentItem.getCounter() - 1);
            itemImage.setImage(new Image("file:" + currentItem.getImages().get(currentItem.getCounter()), 523, 425, false, false));
        }
    }

    @FXML
    void handleManageAction() throws IOException {
        loadManageWindow();
        manageStage.showAndWait();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        manageStage.setX((primScreenBounds.getWidth() - manageStage.getWidth()) / 2);
        manageStage.setY((primScreenBounds.getHeight() - manageStage.getHeight()) / 2);
        thisStage = (Stage) usernameField.getScene().getWindow();
    }

    public void loadManageWindow() throws IOException {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("manageItem.fxml"));
        root = loader.load();
        manageStage = new Stage();
        manageStage.initModality(Modality.APPLICATION_MODAL);
        manageStage.setTitle("Manage item");
        manageStage.setResizable(false);
        scene = new Scene(root, 600, 400);
        manageStage.setScene(scene);

    }
     void loadItempage(User user, Item item) throws IOException {
        thisItem = item;
        nameField.setText(user.getFirstName() + " " + user.getLastName());
        usernameField.setText("@" + user.getUsername());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhoneNumber());
        itemnameField.setText(item.getName());
        priceField.setText(item.getPrice());
        DescriptionArea.setText(item.getDescription());
        DescriptionArea.setEditable(false);
        DescriptionArea.setWrapText(true);
        currentItem = item;

        if (currentItem.getOwner().equals(LoginController.getUsername()))
            manageButton.setVisible(true);
        else
            manageButton.setVisible(false);

        loadPhoto();
        System.out.println(currentItem.getImages().size());

        if (currentItem.getOwner().equals(LoginController.getUsername()))
            buyButton.setVisible(false);
        else
            buyButton.setVisible(true);

        loadManageWindow();

    }

    void loadPhoto() {
        ID = currentItem.getID();
        itemImage.setImage(new Image("file:" + currentItem.getImages().get(0), 523, 425, false, false));
        System.out.println(ID);
    }

    String getName() {
        return this.nameField.getText();
    }

    public void handleBuyAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("buyItemPage.fxml"));
            root = loader.load();
            BuyItemController bc = loader.getController();
            bc.loadBuyPage(currentItem);
            stage = (Stage) buyButton.getScene().getWindow();
            scene = new Scene(root, 919, 643);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
            stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Item getCurrentItem () {
        return thisItem;
    }
    public static Stage getStage () {
        return thisStage;
    }

}