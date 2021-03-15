package com.project.softeng2coronavirustrackerandroidapp.Repository;

import android.os.StrictMode;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel.PremiumTravelModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeRepository implements IHomeContract.IHomeRepository {
    private static HomeRepository instance = null;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.covid19api.com/";
    private static final String API_KEY_COVID19_API = "5cf9dfd5-3449-485e-b5ae-70a60e997864";

    private HomeRepository() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
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
    public PhStatusModel fetchPhData() throws IOException {
        PhStatusModel phStatusModel = null;
        HomeApiService service = retrofit.create(HomeApiService.class);
        Call<PhStatusModel> call = service.totalCasesPh();
        Response<PhStatusModel> response = call.execute();
        if (response.body() != null) {
            phStatusModel = response.body();
        }
        return phStatusModel;
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
    public PremiumTravelModel fetchPremiumData() throws IOException {
        PremiumTravelModel premiumTravelModel = null;
        HomeApiService service = retrofit.create(HomeApiService.class);
        Call<PremiumTravelModel> call = service.premiumTravelData(API_KEY_COVID19_API);
        Response<PremiumTravelModel> response = call.execute();
        if (response.body() != null) {
            premiumTravelModel = response.body();
        }
        return premiumTravelModel;
    }

    @Override
    public List<DailyPhStatusModel> fetchPhDailyData() throws IOException {
        List<DailyPhStatusModel> dailyPhStatusModelList = null;
        HomeApiService service = retrofit.create(HomeApiService.class);
        Call<List<DailyPhStatusModel>> call = service.listOfCasesPhDaily();
        Response<List<DailyPhStatusModel>> response = call.execute();
        if (response.body() != null) {
            dailyPhStatusModelList = new ArrayList<>(response.body());
            Collections.reverse(dailyPhStatusModelList);
        }
        return dailyPhStatusModelList;
    }
}
