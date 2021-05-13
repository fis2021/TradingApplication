package org.fis.ta.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.model.Item;
import org.fis.ta.services.ItemService;

import java.io.IOException;

public class DeleteAlertBoxController {

    private static final ObjectRepository<Item> items = ItemService.getItemRepository();
    private Stage stage;
    @FXML private Text text;
    @FXML
    void handleNoAction() {
        stage=(Stage) text.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleYesAction() throws IOException {
       ItemService.deleteItem(ItempageController.getCurrentItem());
       stage = (Stage) text.getScene().getWindow();
       stage.close();
       Parent root;
       FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("saleslist.fxml"));
       root=loader.load();
       Stage bgStage = SaleslistController.getThisStage();
       Scene scene = new Scene(root,919,643);
       bgStage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        bgStage.setX((primScreenBounds.getWidth() - bgStage.getWidth())/2);
        bgStage.setY((primScreenBounds.getHeight()-bgStage.getHeight())/2);
    }

}
