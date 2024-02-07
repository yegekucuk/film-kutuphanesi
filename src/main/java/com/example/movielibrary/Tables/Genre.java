package com.example.movielibrary.Tables;

public class Genre {
    public int GenreID;
    public String GenreName;

    public Genre(int genreID, String genreName) {
        GenreID = genreID;
        GenreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "GenreID=" + GenreID +
                ", GenreName='" + GenreName + '\'' +
                '}';
    }
}
