package com.project.softeng2coronavirustrackerandroidapp.Models;

import com.google.gson.annotations.SerializedName;

public class WorldTotalModel {
    @SerializedName("TotalConfirmed")
    private int totalConfirmed;
    @SerializedName("TotalDeaths")
    private int totalDeaths;
    @SerializedName("TotalRecovered")
    private int totalRecovered;

    public WorldTotalModel(int totalConfirmed, int totalDeaths, int totalRecovered) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    @Override
    public String toString() {
        return "WorldTotalModel{" +
                "totalConfirmed=" + totalConfirmed +
                ", totalDeaths=" + totalDeaths +
                ", totalRecovered=" + totalRecovered +
                '}';
    }
}
