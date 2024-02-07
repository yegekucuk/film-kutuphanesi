package com.example.movielibrary;

import com.example.movielibrary.Tables.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateAnAccountController implements Initializable {
    ArrayList<User> users;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ComboBox<Integer> age;
    @FXML
    private TextField mail;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Label warning_label;

    @FXML
    void create() {
        try {
            // Load values
            String g = String.valueOf(gender.getItems().get(0).charAt(0));
            int a = age.getValue();
            String m = mail.getText();
            String p = password.getText();
            String u = username.getText();
            if (p.isBlank() || u.isBlank()) {
                throw new Exception("Can't be blank!");
            }

            // Load connection and statement
            Connection con = Main.sqlConnect();
            Statement stmt = con.createStatement();

            // Execute the statement
            String statementStr = String.format("INSERT INTO Users VALUES ('%s','%s','%s',%d,'%s');",m,p,u,a,g);
            stmt.execute(statementStr);

            // Go back to the login screen
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("log-in.fxml"));
            Main.mainStage.setScene(new Scene(loader.load()));
        }
        catch (Exception e) {
            warning_label.setText(e.getMessage());
        }
    }

    @FXML
    void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("log-in.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.mainStage.setTitle("Movie Library App Create-Account");
        // Getting users
        users = Main.getUsers();

        // Setting genders
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        gender.setItems(FXCollections.observableArrayList(genders));

        // Setting age
        ArrayList<Integer> numbersList = new ArrayList<>();

        for (int i = 18; i <= 99; i++) {
            numbersList.add(i);
        }
        age.setItems(FXCollections.observableArrayList(numbersList));
    }
}
