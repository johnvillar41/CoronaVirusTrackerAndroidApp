package com.project.softeng2coronavirustrackerandroidapp.Repository;

import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeApiService {
    @GET("total/country/philippines")
    Call<List<PhStatusModel>> listOfPhStatus();
}
