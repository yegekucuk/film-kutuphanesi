package com.example.movielibrary.Tables;

public class Character {
    public int CharacterID;
    public String CharacterName;
    public String CharacterSurname;
    public int CharacterAge;
    public int FilmID;
    public int ActorID;

    public Character(int characterID, String characterName, String characterSurname, int characterAge, int filmID, int actorID) {
        CharacterID = characterID;
        CharacterName = characterName;
        CharacterSurname = characterSurname;
        CharacterAge = characterAge;
        FilmID = filmID;
        ActorID = actorID;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "CharacterID=" + CharacterID +
                ", CharacterName='" + CharacterName + '\'' +
                ", CharacterSurname='" + CharacterSurname + '\'' +
                ", CharacterAge=" + CharacterAge +
                ", FilmID=" + FilmID +
                ", ActorID=" + ActorID +
                '}';
    }
}
