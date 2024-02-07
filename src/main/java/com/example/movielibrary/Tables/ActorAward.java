package com.example.movielibrary.Tables;

public class ActorAward {
    public int Award_A_ID;
    public String AwardName;
    public String AwardGivingOrganization;

    public ActorAward(int award_A_ID, String awardName, String awardGivingOrganization) {
        Award_A_ID = award_A_ID;
        AwardName = awardName;
        AwardGivingOrganization = awardGivingOrganization;
    }

    @Override
    public String toString() {
        return "ActorAwards{" +
                "Award_A_ID=" + Award_A_ID +
                ", AwardName='" + AwardName + '\'' +
                ", AwardGivingOrganization='" + AwardGivingOrganization + '\'' +
                '}';
    }
}
