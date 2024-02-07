package com.example.movielibrary.Tables;

public class Scenarist {
    public int ScenaristID;
    public String ScenaristName;
    public String ScenaristSurname;
    public int ScenaristAge;
    public String ScenaristGender;

    public Scenarist(int scenaristID, String scenaristName, String scenaristSurname, int scenaristAge, String scenaristGender) {
        ScenaristID = scenaristID;
        ScenaristName = scenaristName;
        ScenaristSurname = scenaristSurname;
        ScenaristAge = scenaristAge;
        ScenaristGender = scenaristGender;
    }

    @Override
    public String toString() {
        return "Scenarist{" +
                "ScenaristID=" + ScenaristID +
                ", ScenaristName='" + ScenaristName + '\'' +
                ", ScenaristSurname='" + ScenaristSurname + '\'' +
                ", ScenaristAge=" + ScenaristAge +
                ", ScenaristGender='" + ScenaristGender + '\'' +
                '}';
    }
}
