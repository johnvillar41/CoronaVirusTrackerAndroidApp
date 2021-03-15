package com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel;

import com.google.gson.annotations.SerializedName;

public class PremiumTravelModel {
    @SerializedName("Recommendation")
    private String recommendation;
    @SerializedName("Level")
    private LevelModel level;
    @SerializedName("Notes")
    private NotesModel notesModel;

    public PremiumTravelModel(String recommendation, LevelModel level, NotesModel notesModel) {
        this.recommendation = recommendation;
        this.level = level;
        this.notesModel = notesModel;
    }

    public PremiumTravelModel() {
    }

    public String getRecommendation() {
        return recommendation;
    }

    public LevelModel getLevel() {
        return level;
    }

    public NotesModel getNotesModel() {
        return notesModel;
    }
}
