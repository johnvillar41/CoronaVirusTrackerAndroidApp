package com.project.softeng2coronavirustrackerandroidapp.Repository;

import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel.PremiumTravelModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel.SummaryModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeApiService {
    @GET("https://api.apify.com/v2/key-value-stores/lFItbkoNDXKeSWBBA/records/LATEST?disableRedirect=true")
    Call<PhStatusModel> totalCasesPh();

    @GET("world/total")
    Call<WorldTotalModel> totalCases();

    @GET("summary")
    Call<SummaryModel> summaryOfCases();

    @GET("premium/travel/country/philippines?")
    Call<PremiumTravelModel> premiumTravelData(@Query("Key")String apiKey);
}
