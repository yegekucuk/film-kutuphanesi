package com.example.movielibrary.Tables;

public class FilmAward {
    public int Award_F_ID;
    public String AwardName;
    public String AwardGivingOrganization;

    public FilmAward(int award_F_ID, String awardName, String awardGivingOrganization) {
        Award_F_ID = award_F_ID;
        AwardName = awardName;
        AwardGivingOrganization = awardGivingOrganization;
    }

    @Override
    public String toString() {
        return "FilmAward{" +
                "Award_F_ID=" + Award_F_ID +
                ", AwardName='" + AwardName + '\'' +
                ", AwardGivingOrganization='" + AwardGivingOrganization + '\'' +
                '}';
    }
}
