package com.example.movielibrary.Tables;

public class Actor {
    public int ActorID;
    public String ActorName;
    public String ActorSurname;
    public int ActorAge;
    public String ActorGender;

    public Actor(int actorID, String actorName, String actorSurname, int actorAge, String actorGender) {
        ActorID = actorID;
        ActorName = actorName;
        ActorSurname = actorSurname;
        ActorAge = actorAge;
        ActorGender = actorGender;
    }

    @Override
    public String toString() {
        return "Actors{" +
                "ActorID=" + ActorID +
                ", ActorName='" + ActorName + '\'' +
                ", ActorSurname='" + ActorSurname + '\'' +
                ", ActorAge=" + ActorAge +
                ", ActorGender='" + ActorGender + '\'' +
                '}';
    }
}
