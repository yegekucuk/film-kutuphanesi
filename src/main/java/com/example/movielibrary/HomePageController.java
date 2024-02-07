package com.example.movielibrary;

import com.example.movielibrary.Tables.Film;
import com.example.movielibrary.Tables.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.movielibrary.Main.sqlConnect;

public class HomePageController implements Initializable {
    User user;
    ArrayList<Film> films;
    int pageMax;
    AtomicInteger page;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private TextField counter;

    @FXML
    private Text label_username;

    @FXML
    private TextField search_textBox;

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
    // Liked films
    public void movies() {
        // Clearing the hBoxes
        hbox1.getChildren().clear();
        hbox2.getChildren().clear();

        // Clearing the films ArrayList
        films.clear();

        try {
            // Loading connection and statement
            Connection con = sqlConnect();
            Statement stmt = con.createStatement();

            // Executing statement
            String statementStr = String.format("SELECT FilmID, FilmName FROM Films WHERE FilmID in (SELECT Films.FilmID FROM LikedFilms INNER JOIN Films ON Films.FilmID = LikedFilms.FilmID INNER JOIN Users ON Users.UserID = LikedFilms.UserID WHERE Users.UserID = %d) ORDER BY FilmName", user.UserID);
            ResultSet rset = stmt.executeQuery(statementStr);
            while (rset.next()) {
                films.add(new Film(
                        rset.getInt("FilmID"),
                        rset.getString("FilmName")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // New maximum numbers of page
        pageMax = (int) Math.floor(films.size() / 8.01) + 1;
        // Checking if number of films is smaller than the number "Last Film of Page"
        int pageLastFilm = (8 * page.get()) - 1;
        int getMin = Math.min(films.size() - 1, pageLastFilm);
        fillTheScreen(getMin);
    }

    @FXML
    void profile() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("profile.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @FXML
    void search() {
        // Getting searched text
        String text = search_textBox.getText();
        if (!text.isBlank()) {
            // Clearing the hBoxes
            hbox1.getChildren().clear();
            hbox2.getChildren().clear();

            // Clearing the films ArrayList
            films.clear();

            try {
                // Loading connection and statement
                Connection con = sqlConnect();
                Statement stmt = con.createStatement();

                // Executing statement
                String statementStr = "Select * from Films where FilmName like '" + text + "%'";
                statementStr += (" " + "order by FilmName");
                ResultSet rset = stmt.executeQuery(statementStr);
                while (rset.next()) {
                    films.add(new Film(
                            rset.getInt("FilmID"),
                            rset.getString("FilmName"),
                            rset.getString("Summary"),
                            rset.getString("PrimaryLanguage"),
                            rset.getString("FilmPlatform"),
                            rset.getInt("Year"),
                            rset.getInt("RunTimeMin"),
                            rset.getInt("DirectorID"),
                            rset.getInt("ScenaristID"),
                            rset.getInt("GenreID")
                    ));
                }
                con.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }

            // New maximum numbers of page
            pageMax = (int)Math.floor(films.size()/8.01) + 1;
            // Checking if number of films is smaller than the number "Last Film of Page"
            int pageLastFilm = (8* page.get())-1;
            int getMin = Math.min(films.size()-1, pageLastFilm);
            fillTheScreen(getMin);
        }
        else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("film-app.fxml"));
            try {
                Main.mainStage.setScene(new Scene(loader.load()));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void fillTheScreen(int min) {
        // Setting properties width, height and spacing
        int vBoxPrefWidth = 169; // 170 - 1
        int vBoxPrefHeight = 202;
        int imageViewFitWidth = 165;
        int imageViewFitHeight = 162;
        int labelPrefWidth = 165;
        int labelPreHeight = 26;
        int hBox1spacing = 40;
        int hBox2spacing = 40;
        int processedFilms = 0;

        // Adding films to the boxes
        for (int i = 8*(page.get() -1); i <= min; i++) {
            // Declaring variables
            Film film = films.get(i);
            VBox vBox = new VBox(1);
            ImageView imageView = new ImageView();
            Label label = new Label();

            // Setting properties of vBox
            vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            vBox.setPrefWidth(vBoxPrefWidth);
            vBox.setPrefHeight(vBoxPrefHeight);
            vBox.setAlignment(Pos.CENTER);
            vBox.setCursor(Cursor.HAND);

            // Setting properties of Image and imageView
            imageView.setPreserveRatio(false);
            Image image = new Image(String.format("File:src/main/resources/com/example/movielibrary/img/films/%d.jpg",film.FilmID));
            imageView.setImage(image);
            imageView.setFitWidth(imageViewFitWidth);
            imageView.setFitHeight(imageViewFitHeight);

            // Setting properties of label
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font("System", 16));
            label.setTextFill(Color.WHITE);
            label.setPrefWidth(labelPrefWidth);
            label.setPrefHeight(labelPreHeight);
            label.setBackground(new Background(new BackgroundFill(Color.web("#1d3042"), null, null)));
            label.setText(film.FilmName);

            // Adding imageView and label to the vBox
            vBox.getChildren().addAll(imageView, label);

            // Setting hBox spacing property
            hbox1.setSpacing(hBox1spacing);
            hbox2.setSpacing(hBox2spacing);

            // VBox function on mouse click
            vBox.setOnMouseClicked(e -> {
                MoviesController.MovieID = film.FilmID;
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("movies.fxml"));
                try {
                    Main.mainStage.setScene(new Scene(loader.load()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // Adding vBox to hBoxes
            if (processedFilms <= films.size()) {
                if (hbox1.getChildren().size() < 4) {
                    hbox1.getChildren().add(vBox);
                    processedFilms++;
                }
                else if (hbox2.getChildren().size() < 4) {
                    hbox2.getChildren().add(vBox);
                    processedFilms++;
                }
                else {
                    break;
                }
            }
            else {
                break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the information of the user who logged in
        try {
            // Loading connection and statement
            Connection con = sqlConnect();
            Statement stmt = con.createStatement();
            // Executing statement
            String statementStr = String.format("Select * from Users where UserID = %d",LoginController.ID);
            ResultSet rset = stmt.executeQuery(statementStr);
            while (rset.next()) {
                user = new User(
                        rset.getInt("UserID"),
                        rset.getString("Mail"),
                        rset.getString("Password"),
                        rset.getString("Username"),
                        rset.getInt("UserAge"),
                        rset.getString("UserGender")
                );
            }
            // Closing connection
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // Update label_username
        label_username.setText(user.Username);

        // Get films
        films = Main.getFilms();

        // Setting variables page and pageMax
        // The variable pageMax refers to maximum numbers of page the program can have, according to number of films in the dataset
        page = new AtomicInteger(Integer.parseInt(counter.getText()));
        pageMax = (int)Math.floor(films.size()/8.01) + 1;

        fillTheScreen((8* page.get())-1);

        button1.setOnAction(e -> {
            // If page number is not 1
            if (page.get() != 1) {
                // Decreasing the int page
                page.getAndDecrement();
                counter.setText(String.valueOf(page));

                // Clearing the hBoxes
                hbox1.getChildren().clear();
                hbox2.getChildren().clear();

                // Checking if number of films is smaller than the number "Last Film of Page"
                int pageLastFilm = (8* page.get())-1;
                int getMin = Math.min(films.size()-1, pageLastFilm);
                fillTheScreen(getMin);
            }
        });

        button2.setOnAction(e -> {
            if (page.get() != pageMax) {
                page.getAndIncrement();
                counter.setText(String.valueOf(page));

                // Clearing the hBoxes
                hbox1.getChildren().clear();
                hbox2.getChildren().clear();

                // Checking if number of films is smaller than the number "Last Film of Page"
                int pageLastFilm = (8* page.get())-1;
                int getMin = Math.min(films.size()-1, pageLastFilm);
                fillTheScreen(getMin);

            }
        });
        System.out.println("HomePageController.java is running.");
    }
}
