package com.example.movielibrary.Tables;

public class Director {
    public int DirectorID;
    public String DirectorName;
    public String DirectorSurname;
    public int DirectorAge;
    public String DirectorGender;

    public Director(int directorID, String directorName, String directorSurname, int directorAge, String directorGender) {
        DirectorID = directorID;
        DirectorName = directorName;
        DirectorSurname = directorSurname;
        DirectorAge = directorAge;
        DirectorGender = directorGender;
    }

    @Override
    public String toString() {
        return "Director{" +
                "DirectorID=" + DirectorID +
                ", DirectorName='" + DirectorName + '\'' +
                ", DirectorSurname='" + DirectorSurname + '\'' +
                ", DirectorAge=" + DirectorAge +
                ", DirectorGender='" + DirectorGender + '\'' +
                '}';
    }
}
