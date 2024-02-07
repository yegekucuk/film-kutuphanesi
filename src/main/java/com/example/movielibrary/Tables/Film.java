package com.example.movielibrary.Tables;

public class Film {
    public int FilmID;
    public String FilmName;
    public String Summary;
    public String PrimaryLanguage;
    public String FilmPlatform;
    public int Year;
    public int RunTimeMin;
    public int DirectorID;
    public int ScenaristID;
    public int GenreID;

    public Film(int filmID, String filmName, String summary, String primaryLanguage, String filmPlatform, int year, int runTimeMin, int directorID, int scenaristID, int genreID) {
        FilmID = filmID;
        FilmName = filmName;
        Summary = summary;
        PrimaryLanguage = primaryLanguage;
        FilmPlatform = filmPlatform;
        Year = year;
        RunTimeMin = runTimeMin;
        DirectorID = directorID;
        ScenaristID = scenaristID;
        GenreID = genreID;
    }

    public Film(int filmID, String filmName) {
        FilmID = filmID;
        FilmName = filmName;
    }

    @Override
    public String toString() {
        return "Film{" +
                "FilmID=" + FilmID +
                ", FilmName='" + FilmName + '\'' +
                ", Summary='" + Summary + '\'' +
                ", PrimaryLanguage='" + PrimaryLanguage + '\'' +
                ", FilmPlatform='" + FilmPlatform + '\'' +
                ", Year=" + Year +
                ", RunTimeMin=" + RunTimeMin +
                ", DirectorID=" + DirectorID +
                ", ScenaristID=" + ScenaristID +
                ", GenreID=" + GenreID +
                '}';
    }
}
