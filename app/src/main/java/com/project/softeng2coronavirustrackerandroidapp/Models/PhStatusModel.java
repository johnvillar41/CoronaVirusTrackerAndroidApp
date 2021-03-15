package com.project.softeng2coronavirustrackerandroidapp.Models;

import com.google.gson.annotations.SerializedName;

public class PhStatusModel {
    private int infected;
    private int tested;
    private int recovered;
    private int deceased;
    private int activeCases;
    private int unique;

    public PhStatusModel(int infected, int tested, int recovered, int deceased, int activeCases, int unique) {
        this.infected = infected;
        this.tested = tested;
        this.recovered = recovered;
        this.deceased = deceased;
        this.activeCases = activeCases;
        this.unique = unique;
    }

    public PhStatusModel() {
    }

    public int getInfected() {
        return infected;
    }

    public int getTested() {
        return tested;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeceased() {
        return deceased;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public int getUnique() {
        return unique;
    }
}
