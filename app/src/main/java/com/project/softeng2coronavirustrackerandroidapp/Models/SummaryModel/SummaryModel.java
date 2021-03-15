package com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryModel {
    @SerializedName("ID")
    private String id;
    @SerializedName("Message")
    private String message;
    @SerializedName("Global")
    private GlobalCasesModel global;
    @SerializedName("Countries")
    private List<CountriesCasesModel> countries;

    public SummaryModel(String id, String message, GlobalCasesModel global, List<CountriesCasesModel> countries) {
        this.id = id;
        this.message = message;
        this.global = global;
        this.countries = countries;
    }

    public SummaryModel() {
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public GlobalCasesModel getGlobal() {
        return global;
    }

    public List<CountriesCasesModel> getCountries() {
        return countries;
    }
}
