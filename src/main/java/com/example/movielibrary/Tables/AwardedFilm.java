package com.example.movielibrary.Tables;

public class AwardedFilm {
    public int FilmID;
    public int AwardID;

    public AwardedFilm(int filmID, int awardID) {
        FilmID = filmID;
        AwardID = awardID;
    }

    @Override
    public String toString() {
        return "AwardedFilms{" +
                "FilmID=" + FilmID +
                ", AwardID=" + AwardID +
                '}';
    }
}
