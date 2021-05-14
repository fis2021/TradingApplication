package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.exceptions.PriceNotValidException;
import org.fis.ta.model.Item;
import org.fis.ta.services.ItemService;
import org.fis.ta.services.UserService;

import java.io.IOException;
import java.util.regex.Pattern;

public class EditItemController {

    private static final ObjectRepository<Item> items = ItemService.getItemRepository();
    @FXML
    private TextField priceField;

    @FXML
    private Text errorMessage;

    @FXML
    private TextField descriptionField;

    private Stage stage;
    private Stage bgStage;
    private Scene scene;
    private Parent root;
    private FXMLLoader loader;
    @FXML
    void handleEditAction() throws IOException, PriceNotValidException {
        Item item;
        item = ItempageController.getCurrentItem();

        try {
            String priceRegex ="\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";
            Pattern pat = Pattern.compile(priceRegex);
            if (!pat.matcher(priceField.getText()).matches())
                throw new PriceNotValidException();
            item.setPrice(priceField.getText());
            item.setDescription(descriptionField.getText());
            items.update(item);
            stage=(Stage) priceField.getScene().getWindow();
            stage.close();
            bgStage = SaleslistController.getThisStage();
            loader = new FXMLLoader(getClass().getClassLoader().getResource("itempage.fxml"));
            root = loader.load();
            ItempageController ic = loader.getController();
            ic.loadItempage(UserService.getCurrentUser(LoginController.getUsername()),ItempageController.getCurrentItem());
            scene = new Scene(root,919,643);
            bgStage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            bgStage.setX((primScreenBounds.getWidth() - bgStage.getWidth())/2);
            bgStage.setY((primScreenBounds.getHeight()-bgStage.getHeight())/2);
        }
        catch(PriceNotValidException e){
            errorMessage.setText(e.getMessage());
        }
    }

    public void loadEditFields()
    {
        Item item = ItempageController.getCurrentItem();
        priceField.setText(item.getPrice());
        descriptionField.setText(item.getDescription());
    }

}
