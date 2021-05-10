package org.fis.ta.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.model.Item;
import org.fis.ta.model.User;
import org.fis.ta.services.ItemService;
import org.fis.ta.services.UserService;

import java.io.IOException;

public class DisplayItemsPageController {

    private static final ObjectRepository<Item> items = ItemService.getItemRepository();
    private static final ObjectRepository<User> users = UserService.getUserRepository();

    @FXML
    private Text displayMessage;

    @FXML
    private TableView itemsTableView;
    @FXML
    private void initialize(){
        fillTable();
        addButtonToTable();
    }


    TableColumn<ImageView, ImageView> col1 = new TableColumn<>("Picture");
    TableColumn<Item, String> col2 = new TableColumn<>("Name");
    TableColumn<Item, String> col3 = new TableColumn<>("Price");


    public void fillTable(){
        col1.setMinWidth(100);

        col2.setMinWidth(100);
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

        col3.setMinWidth(100);
        col3.setCellValueFactory(new PropertyValueFactory<>("price"));

        if(itemsTableView.getColumns()!=null){
            itemsTableView.getColumns().add(col1);
            itemsTableView.getColumns().add(col2);
            itemsTableView.getColumns().add(col3);
        }



        for(Item item : items.find()){
            if(item.getCategory().equals(CategoryPageController.getCategory())) {
                itemsTableView.getItems().add(item);
                /*try{
                    ImageView imageView = new ImageView(item.getImage());
                    itemsTableView.getItems().add(imageView);
                }catch (FileNotFoundException e){}*/

            }
        }

    }


    private void addButtonToTable() {
        TableColumn<Item, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btn = new Button("View");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                                Item selectedItem = getTableView().getItems().get(getIndex());
                                for (User user : users.find()) {
                                    if (selectedItem.getOwner().equals(user.getUsername())){

                                        //ItempageController.loadItempage(user,selectedItem);
                                        break;
                                    }

                                }

                                Stage stage =(Stage) displayMessage.getScene().getWindow();
                                Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("itempage.fxml"));
                                Scene scene = new Scene(viewRegisterRoot, 600, 600);
                                stage.setScene(scene);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        itemsTableView.getColumns().add(colBtn);

    }



    public void handleBackAction(ActionEvent actionEvent) {
        try {
            Stage stage =(Stage) displayMessage.getScene().getWindow();
            Parent viewRegisterRoot = FXMLLoader.load(getClass().getClassLoader().getResource("categoryPage.fxml"));
            Scene scene = new Scene(viewRegisterRoot, 600, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
