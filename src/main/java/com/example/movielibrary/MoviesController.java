package com.example.movielibrary;

import com.example.movielibrary.Tables.Film;
import com.example.movielibrary.Tables.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.movielibrary.Main.sqlConnect;

public class MoviesController implements Initializable {
    boolean thisIsLiked;
    User user;
    public static int MovieID;

    @FXML
    private Label country_name;

    @FXML
    private Label director_name;

    @FXML
    private Label film_genre;

    @FXML
    private ImageView imageView;

    @FXML
    private Label film_name;

    @FXML
    private TextArea film_summary;

    @FXML
    private Text label_username;

    @FXML
    private ImageView like_button;

    @FXML
    private Label run_time;

    @FXML
    private Label scenarist_name;

    @FXML
    private TextField search_textBox;

    @FXML
    private Label year;

    @FXML
    private HBox hbox_awards;

    @FXML
    private HBox hbox_stars;

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
    void movies() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("film-app.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @FXML
    void profile() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("profile.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @FXML
    void search() {

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

        // Getting properties of the Film
        try {
            Connection con = Main.sqlConnect();
            Statement stmt = con.createStatement();
            String statementStr = "SELECT FilmName, Summary, PrimaryCountry, Year, RunTimeMin, DirectorName + ' ' + DirectorSurname as Director, ScenaristName + ' ' + ScenaristSurname AS Scenarist, GenreName AS Genre FROM Films LEFT JOIN Directors on Films.DirectorID = Directors.DirectorID LEFT JOIN Scenarists ON Films.ScenaristID = Scenarists.ScenaristID LEFT JOIN Genres ON Films.GenreID = Genres.GenreID WHERE FilmID = " + MovieID;
            System.out.println(statementStr);
            ResultSet rset2 = stmt.executeQuery(statementStr);
            while (rset2.next()) {
                film_name.setText(rset2.getString("FilmName"));
                country_name.setText(rset2.getString("PrimaryCountry"));
                year.setText(String.valueOf(rset2.getInt("Year")));
                run_time.setText(String.valueOf(rset2.getInt("RunTimeMin")));
                film_summary.setText(rset2.getString("Summary"));
                film_genre.setText(rset2.getString("Genre"));
                director_name.setText(rset2.getString("Director"));
                scenarist_name.setText(rset2.getString("Scenarist"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Setting the Image of the Film
        imageView.setPreserveRatio(false);
        Image image = new Image(String.format("File:src/main/resources/com/example/movielibrary/img/films/%d.jpg",MovieID));
        imageView.setImage(image);

        // Getting stars
        try {
            Connection con = Main.sqlConnect();
            Statement stmt = con.createStatement();
            String statementStr = "SELECT Actors.ActorName + ' ' + Actors.ActorSurname AS Actor FROM Actors_Films INNER JOIN Actors ON Actors.ActorID = Actors_Films.ActorID INNER JOIN Films ON Films.FilmID = Actors_Films.FilmID  WHERE Films.FilmID = " + MovieID;
            System.out.println(statementStr);
            ResultSet rset3 = stmt.executeQuery(statementStr);
            while (rset3.next()) {
                Text label_Actor = new Text(rset3.getString("Actor"));
                label_Actor.setFill(Color.web("#7fc3ff"));
                hbox_stars.getChildren().addAll(label_Actor);
            }
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Getting awards
        try {
            Connection con = Main.sqlConnect();
            Statement stmt = con.createStatement();
            String statementStr = "SELECT AwardName FROM AwardedFilms INNER JOIN Awards ON Awards.AwardID = AwardedFilms.AwardID INNER JOIN Films ON Films.FilmID = AwardedFilms.FilmID WHERE Films.FilmID = " + MovieID;
            System.out.println(statementStr);
            ResultSet rset4 = stmt.executeQuery(statementStr);
            while (rset4.next()) {
                Text label_Awards = new Text(rset4.getString("AwardName"));
                label_Awards.setFill(Color.web("#7fc3ff"));
                hbox_awards.getChildren().addAll(label_Awards);
            }
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Getting liked films
        try {
            thisIsLiked = false;
            ArrayList<Film> films = new ArrayList<>();
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
            for (Film film : films) {
                if (MovieID == film.FilmID) {
                    thisIsLiked = true;
                    break;
                }
            }
            Image image2;
            if (thisIsLiked) {
                image2 = new Image("File:src/main/resources/com/example/movielibrary/img/redheart-removebg-preview.png");
            }
            else {
                image2 = new Image("File:src/main/resources/com/example/movielibrary/img/white_heart-removebg-preview.png");
            }
            like_button.setImage(image2);
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        like_button.setOnMouseClicked(e -> {
            try {
                Connection con = sqlConnect();
                Statement stmt = con.createStatement();
                if (thisIsLiked) {
                    String  statementStr2 = String.format("DELETE FROM LikedFilms WHERE UserID = %d AND FilmID = %d", user.UserID, MovieID);
                    stmt.execute(statementStr2);
                }
                else {
                    String  statementStr3 = String.format("INSERT INTO LikedFilms VALUES (%d,%d)", MovieID, user.UserID);
                    stmt.execute(statementStr3);
                }
                con.close();
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("movies.fxml"));
                Main.mainStage.setScene(new Scene(loader.load()));
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.exit(1);
            }

        });
    }
}
