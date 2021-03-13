package com.project.softeng2coronavirustrackerandroidapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryModel {
    @SerializedName("ID")
    private String id;
    @SerializedName("Message")
    private String message;
    @SerializedName("Global")
    private GlobalModel global;
    @SerializedName("Countries")
    private List<CountriesModel> countries;

    public SummaryModel(String id, String message, GlobalModel global, List<CountriesModel> countries) {
        this.id = id;
        this.message = message;
        this.global = global;
        this.countries = countries;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public GlobalModel getGlobal() {
        return global;
    }

    public List<CountriesModel> getCountries() {
        return countries;
    }
}
