package com.example.movielibrary;

import com.example.movielibrary.Tables.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static int ID;
    ArrayList<User> users;
    @FXML
    private Label label;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void createAnAccount() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("create-an-account.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @FXML
    void login() throws IOException {
        String UsernameIn = username.getText();
        String PasswordIn = password.getText();

        for (User user : users) {
            if (Objects.equals(user.Username, UsernameIn) && Objects.equals(user.Password, PasswordIn)) {
                System.out.println("Logged in as " + user.Username);
                // SELECT * from Users WHERE Username = admin.Username
                ID = user.UserID;
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("film-app.fxml"));
                Main.mainStage.setScene(new Scene(loader.load()));
            }
            else {
                label.setText("Invalid username or password!");
            }
        }
    }

    @FXML
    void about() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("about.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.mainStage.setTitle("Movie Library App Log-in");
        users = Main.getUsers();
    }
}
