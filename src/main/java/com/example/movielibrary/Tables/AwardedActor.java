package com.example.movielibrary.Tables;

public class AwardedActor {
    public int ActorID;
    public int AwardID;

    public AwardedActor(int actorID, int awardID) {
        ActorID = actorID;
        AwardID = awardID;
    }

    @Override
    public String toString() {
        return "AwardedActors{" +
                "ActorID=" + ActorID +
                ", AwardID=" + AwardID +
                '}';
    }
}
