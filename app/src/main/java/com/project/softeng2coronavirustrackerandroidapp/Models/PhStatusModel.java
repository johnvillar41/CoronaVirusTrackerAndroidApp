package com.project.softeng2coronavirustrackerandroidapp.Models;

import com.google.gson.annotations.SerializedName;

public class PhStatusModel {
    @SerializedName("Country")
    private String country;
    @SerializedName("CountryCode")
    private String countryCode;
    @SerializedName("Province")
    private String province;
    @SerializedName("City")
    private String city;
    @SerializedName("CityCode")
    private String cityCode;
    @SerializedName("Lat")
    private String latitude;
    @SerializedName("Lon")
    private String longitude;
    @SerializedName("Confirmed")
    private int confirmed;
    @SerializedName("Deaths")
    private int deaths;
    @SerializedName("Recovered")
    private int recovered;
    @SerializedName("Active")
    private int active;
    @SerializedName("Date")
    private String date;

    public PhStatusModel(String country, String countryCode, String province, String city, String cityCode, String latitude, String longitude, int confirmed, int deaths, int recovered, int active, String date) {
        this.country = country;
        this.countryCode = countryCode;
        this.province = province;
        this.city = city;
        this.cityCode = cityCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "PhStatusModel{" +
                "country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", lat='" + latitude + '\'' +
                ", lon='" + longitude + '\'' +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", date='" + date + '\'' +
                '}';
    }
}
