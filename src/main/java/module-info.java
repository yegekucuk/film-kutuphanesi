module com.example.movielibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.movielibrary to javafx.fxml;
    exports com.example.movielibrary;
    exports com.example.movielibrary.Tables;
    opens com.example.movielibrary.Tables to javafx.fxml;
}