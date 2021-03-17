package com.project.softeng2coronavirustrackerandroidapp.Models;

public class MclTotalModel {
    private int totalConfirmed;
    private int totalTested;
    private int totalDeaths;
    private int totalRecovered;

    public MclTotalModel(int totalConfirmed, int totalTested, int totalDeaths, int totalRecovered) {
        this.totalConfirmed = totalConfirmed;
        this.totalTested = totalTested;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getTotalTested() {
        return totalTested;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }
}
