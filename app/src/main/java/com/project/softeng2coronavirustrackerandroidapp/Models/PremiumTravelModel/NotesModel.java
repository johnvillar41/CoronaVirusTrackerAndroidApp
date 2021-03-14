package com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel;

import com.google.gson.annotations.SerializedName;

public class NotesModel {
    @SerializedName("ID")
    private String id;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Note")
    private String note;
    @SerializedName("Date")
    private String date;

    public NotesModel(String id, String countryCode, String note, String date) {
        this.id = id;
        this.countryCode = countryCode;
        this.note = note;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }
}
