package com.project.softeng2coronavirustrackerandroidapp.Models;

import com.google.gson.annotations.SerializedName;

public class DailyPhStatusModel {
    private int infected;
    private String tested;
    private int recovered;
    private int deceased;
    @SerializedName("PUIs")
    private int personUnderInvestigation;
    @SerializedName("PUMs")
    private int personUnderMonitoring;
    @SerializedName("lastUpdatedAtApify")
    private String date;
    private int infectedIncrease;

    public DailyPhStatusModel(int infected, String tested, int recovered, int deceased, int personUnderInvestigation, int personUnderMonitoring, String date) {
        this.infected = infected;
        this.tested = tested;
        this.recovered = recovered;
        this.deceased = deceased;
        this.personUnderInvestigation = personUnderInvestigation;
        this.personUnderMonitoring = personUnderMonitoring;
        this.date = date;
    }

    public int getInfectedIncrease() {
        return infectedIncrease;
    }

    public void setInfectedIncrease(int infectedIncrease) {
        this.infectedIncrease = infectedIncrease;
    }

    public String getDate() {
        return date;
    }

    public int getInfected() {
        return infected;
    }

    public String getTested() {
        return tested;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeceased() {
        return deceased;
    }

    public int getPersonUnderInvestigation() {
        return personUnderInvestigation;
    }

    public int getPersonUnderMonitoring() {
        return personUnderMonitoring;
    }

    @Override
    public String toString() {
        return "DailyPhStatusModel{" +
                "infected=" + infected +
                ", tested=" + tested +
                ", recovered=" + recovered +
                ", deceased=" + deceased +
                ", personUnderInvestigation=" + personUnderInvestigation +
                ", personUnderMonitoring=" + personUnderMonitoring +
                '}';
    }
}
