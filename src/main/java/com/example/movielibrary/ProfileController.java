package com.example.movielibrary;

import com.example.movielibrary.Tables.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

;

public class ProfileController implements Initializable {
    int ID;
    User currentUser;
    @FXML
    private Label label_error;
    @FXML
    private Label label_error2;
    @FXML
    private Text label_username1;

    @FXML
    private TextField search_textBox;

    @FXML
    private TextField user_email;

    @FXML
    private TextField user_newpassword;

    @FXML
    private TextField user_newpassword2;

    @FXML
    private TextField user_oldpassword;

    @FXML
    private TextField user_username;

    @FXML
    void actors() {

    }

    @FXML
    void directors() {

    }

    @FXML
    void genres() {

    }

    @FXML
    void home() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("film-app.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @FXML
    void movies() {

    }

    @FXML
    void profile() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("profile.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @FXML
    void search() {

    }

    @FXML
    void removeAccount() throws IOException {
        try {
            Connection con = Main.sqlConnect();
            Statement stmt = con.createStatement();
            // Delete
            String statementStr;
            statementStr = String.format("DELETE FROM Users WHERE UserID = %d",currentUser.UserID);
            stmt.execute(statementStr);
            con.close();
        }
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        // Log off and change the scene
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("log-in.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @FXML
    void updatePassword() {
        String newPassword;
        if (Objects.equals(user_oldpassword.getText(), currentUser.Password) && Objects.equals(user_newpassword.getText(),user_newpassword2.getText()) && !user_newpassword.getText().isBlank() && !user_newpassword2.getText().isBlank()) {
            if (Objects.equals(user_oldpassword.getText(), user_newpassword.getText())) {
                label_error.setText("Please enter different password");
            }
            else {
                newPassword = user_newpassword.getText();
                try {
                    Connection con = Main.sqlConnect();
                    Statement stmt = con.createStatement();
                    // Update
                    String statementStr;
                    statementStr = String.format("UPDATE Users SET Password = '%s' WHERE UserID = %d",newPassword,currentUser.UserID);
                    stmt.execute(statementStr);
                    label_error.setText("Successfully changed the Password");
                    currentUser.Mail = user_email.getText();
                    con.close();
                }
                catch (SQLException exception) {
                    System.out.println(exception.getMessage());
                    label_error2.setText(exception.getMessage());
                }
                currentUser.Password = newPassword;
                user_oldpassword.setText("");
                user_newpassword.setText("");
                user_newpassword2.setText("");
            }
        }
        else {
            label_error.setText("Error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Getting ID of the User
        ID = LoginController.ID;

        // Getting user's data with SQL
        try {
            Connection con = Main.sqlConnect();
            Statement stmt = con.createStatement();
            String statementStr = String.format("SELECT * FROM Users WHERE UserID = %d",ID);
            ResultSet rset = stmt.executeQuery(statementStr);
            while (rset.next()) {
                currentUser = new User(
                        rset.getInt("UserID"),
                        rset.getString("Mail"),
                        rset.getString("Password"),
                        rset.getString("Username"),
                        rset.getInt("UserAge"),
                        rset.getString("UserGender")
                );
            }
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // Setting user properties
        user_username.setText(currentUser.Username);
        user_email.setText(currentUser.Mail);
        label_username1.setText(currentUser.Username);


        user_email.setOnAction(e -> {
            if (!user_email.getText().isBlank()) {
                if (!Objects.equals(user_email.getText(), currentUser.Mail)) {
                    try {
                        Connection con = Main.sqlConnect();
                        Statement stmt = con.createStatement();
                        // Update
                        String statementStr;
                        statementStr = String.format("UPDATE Users SET Mail = '%s' WHERE UserID = %d",user_email.getText(),currentUser.UserID);
                        stmt.execute(statementStr);
                        label_error2.setText("Successfully changed the E-Mail");
                        currentUser.Mail = user_email.getText();
                        con.close();
                    }
                    catch (SQLException exception) {
                        System.out.println(exception.getMessage());
                        label_error2.setText(exception.getMessage());
                    }
                }
            }
        });
        user_username.setOnAction(e -> {
            if (!user_username.getText().isBlank()) {
                if (!Objects.equals(user_username.getText(), currentUser.Username)) {
                    try {
                        Connection con = Main.sqlConnect();
                        Statement stmt = con.createStatement();
                        // Update
                        String statementStr;
                        statementStr = String.format("UPDATE Users SET Username = '%s' WHERE UserID = %d",user_username.getText(),currentUser.UserID);
                        stmt.execute(statementStr);
                        label_error2.setText("Successfully changed the Username");
                        currentUser.Username = user_username.getText();
                        label_username1.setText(user_username.getText());
                        con.close();
                    }
                    catch (SQLException exception) {
                        System.out.println(exception.getMessage());
                        label_error2.setText(exception.getMessage());
                    }
                }
            }
        });
    }
}
