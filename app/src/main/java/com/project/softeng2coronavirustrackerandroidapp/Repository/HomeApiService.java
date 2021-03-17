package com.project.softeng2coronavirustrackerandroidapp.Repository;

import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.MclTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeApiService {
    @GET("https://api.apify.com/v2/key-value-stores/lFItbkoNDXKeSWBBA/records/LATEST?disableRedirect=true")
    Call<PhStatusModel> totalCasesPh();

    @GET("world/total")
    Call<WorldTotalModel> totalCases();

    @GET("https://api.apify.com/v2/datasets/sFSef5gfYg3soj8mb/items?format=json&clean=1")
    Call<List<DailyPhStatusModel>> listOfCasesPhDaily();

    @GET("https://softeng2backendapi.conveyor.cloud/api/MclTotal")
    Call<MclTotalModel> mclTotalCases();
}
