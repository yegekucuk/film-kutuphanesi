package com.example.movielibrary.Tables;

public class User {
    public int UserID;
    public String Mail;
    public String Password;
    public String Username;
    public int UserAge;
    public String UserGender;

    public User(int userID, String mail, String password, String username, int userAge, String userGender) {
        UserID = userID;
        Mail = mail;
        Password = password;
        Username = username;
        UserAge = userAge;
        UserGender = userGender;
    }

    public User(int userID, String mail, String password, String username) {
        UserID = userID;
        Mail = mail;
        Password = password;
        Username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", Mail='" + Mail + '\'' +
                ", Password='" + Password + '\'' +
                ", Username='" + Username + '\'' +
                ", UserAge=" + UserAge +
                ", UserGender=" + UserGender +
                '}';
    }
}
