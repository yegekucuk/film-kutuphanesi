package com.example.movielibrary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    @FXML
    private Label label_actor;

    @FXML
    private Label label_film;

    @FXML
    private VBox vbox_count;

    @FXML
    private VBox vbox_genre;

    @FXML
    void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("log-in.fxml"));
        Main.mainStage.setScene(new Scene(loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Getting Genres
        try {
            Connection con = Main.sqlConnect();
            Statement stmt = con.createStatement();
            String statementStr = "SELECT GenreName, COUNT(FilmID) as Numbers FROM Films RIGHT JOIN Genres on Films.GenreID = Genres.GenreID  GROUP BY GenreName HAVING COUNT(FilmID) > 0";
            ResultSet rset = stmt.executeQuery(statementStr);
            while (rset.next()) {
                Text label_genre = new Text(rset.getString("GenreName"));
                Text label_count = new Text(rset.getString("Numbers"));
                label_genre.setFill(Color.WHITE);
                label_count.setFill(Color.WHITE);
                vbox_genre.getChildren().addAll(label_genre);
                vbox_count.getChildren().addAll(label_count);
            }
            String statementStr2 = "SELECT COUNT(FilmID) AS Total FROM Films " + "UNION ALL " + "SELECT COUNT(ActorID) AS Total FROM Actors";
            ResultSet rset2 = stmt.executeQuery(statementStr2);
            rset2.next();
            label_film.setText("Total number of films: " + rset2.getInt("Total"));
            rset2.next();
            label_actor.setText("Total number of actors: " + rset2.getInt("Total"));
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
