package com.project.softeng2coronavirustrackerandroidapp.Repository;

import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeApiService {
    @GET("total/country/philippines")
    Call<List<PhStatusModel>> listOfPhStatus();

    @GET("world/total")
    Call<WorldTotalModel> totalCases();

    @GET("summary")
    Call<SummaryModel> summaryOfCases();
}
