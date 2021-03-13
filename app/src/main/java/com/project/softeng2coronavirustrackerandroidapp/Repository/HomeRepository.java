package com.project.softeng2coronavirustrackerandroidapp.Repository;

import android.os.StrictMode;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.GlobalCasesModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeRepository implements IHomeContract.IHomeRepository {
    private static HomeRepository instance = null;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.covid19api.com/";

    private HomeRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public static HomeRepository getInstance() {
        if (instance == null) {
            instance = new HomeRepository();
        }
        return instance;
    }

    @Override
    public List<PhStatusModel> fetchPhData() throws IOException {
        List<PhStatusModel> phStatusModelList = new ArrayList<>();
        HomeApiService service = retrofit.create(HomeApiService.class);
        Call<List<PhStatusModel>> call = service.listOfPhStatus();
        Response<List<PhStatusModel>> response = call.execute();
        if (response.body() != null) {
            phStatusModelList.addAll(response.body());
        }
        return phStatusModelList;
    }

    @Override
    public WorldTotalModel fetchWorldCases() throws IOException {
        WorldTotalModel worldTotalModel = null;
        HomeApiService service = retrofit.create(HomeApiService.class);
        Call<WorldTotalModel> call = service.totalCases();
        Response<WorldTotalModel> response = call.execute();
        if (response.body() != null) {
            worldTotalModel = response.body();
        }
        return worldTotalModel;
    }

    @Override
    public GlobalCasesModel fetchGlobalCases() {
        return null;
    }

    @Override
    public SummaryModel fetchSummaryCases() throws IOException {
        SummaryModel model = null;
        HomeApiService service = retrofit.create(HomeApiService.class);
        Call<SummaryModel> call = service.summaryOfCases();
        Response<SummaryModel> response = call.execute();
        if (response.body() != null) {
            model = response.body();
        }
        return model;
    }
}