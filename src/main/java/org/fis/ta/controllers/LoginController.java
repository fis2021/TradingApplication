package org.fis.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.fis.ta.MainApp;
import org.fis.ta.exceptions.AccountDoesNotExistException;
import org.fis.ta.exceptions.EmptyFieldException;
import org.fis.ta.exceptions.UsernameAlreadyExistsException;
import org.fis.ta.model.User;
import org.fis.ta.services.UserService;

import java.io.IOException;

public class LoginController {

    private static String username;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    @FXML
    private Text loginMessage;
    private Stage stage;
    private Parent root;
    private Scene scene;
    private ObjectRepository<User> userRepository = UserService.getUserRepository();

    @FXML
    void handleRegisterAction() throws IOException {
        MainApp m = new MainApp();
        stage = (Stage) passwordField.getScene().getWindow();
        Parent viewRegisterPage = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Scene scene = new Scene(viewRegisterPage,400,400);
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
        stage.setY((primScreenBounds.getHeight()-stage.getHeight())/2);
    }

    @FXML
    public void handleLoginAction() throws IOException, EmptyFieldException, AccountDoesNotExistException {
        try {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                throw new EmptyFieldException();
            }
            if (UserService.checkLoginCredentials(usernameField.getText(), passwordField.getText())) {
                username = usernameField.getText();
                loginMessage.setText("Login successful");
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("homepage.fxml"));
                root = loader.load();
                HomepageController hc = loader.getController();
                hc.loadMessage(username);
                stage = (Stage) passwordField.getScene().getWindow();
                scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            } else
                throw new AccountDoesNotExistException();
        }
        catch(EmptyFieldException | AccountDoesNotExistException e){
            loginMessage.setText(e.getMessage());
        }
    }

    public static String getUsername(){ return username; }

    public static void setUsername(String name){ username = name; }//method for for testing


}
