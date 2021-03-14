package com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel;

import com.google.gson.annotations.SerializedName;

public class LevelModel {
    @SerializedName("ID")
    private String id;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Level")
    private int level;
    @SerializedName("LevelDesc")
    private String levelDescription;
    @SerializedName("Date")
    private String date;

    public LevelModel(String id, String countryCode, int level, String levelDescription, String date) {
        this.id = id;
        this.countryCode = countryCode;
        this.level = level;
        this.levelDescription = levelDescription;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getLevel() {
        return level;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public String getDate() {
        return date;
    }
}
