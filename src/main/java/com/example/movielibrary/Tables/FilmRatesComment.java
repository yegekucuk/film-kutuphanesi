package com.example.movielibrary.Tables;

public class FilmRatesComment {
    public int FilmID;
    public int UserID;
    public int FilmRate;
    public String FilmComment;

    public FilmRatesComment(int filmID, int userID, int filmRate, String filmComment) {
        FilmID = filmID;
        UserID = userID;
        FilmRate = filmRate;
        FilmComment = filmComment;
    }

    @Override
    public String toString() {
        return "FilmRatesComment{" +
                "FilmID=" + FilmID +
                ", UserID=" + UserID +
                ", FilmRate=" + FilmRate +
                ", FilmComment='" + FilmComment + '\'' +
                '}';
    }
}
