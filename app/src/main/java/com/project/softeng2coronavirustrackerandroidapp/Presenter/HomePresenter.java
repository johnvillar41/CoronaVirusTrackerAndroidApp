package com.project.softeng2coronavirustrackerandroidapp.Presenter;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel.PremiumTravelModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;
import java.util.List;

public class HomePresenter implements IHomeContract.IHomePresenter {
    private IHomeContract.IHomeView view;
    private IHomeContract.IHomeRepository repository;

    public HomePresenter(IHomeContract.IHomeView view, IHomeContract.IHomeRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void loadWorldCases() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    view.displayLoadingScreen();
                    WorldTotalModel worldTotalModel = repository.fetchWorldCases();
                    view.displayTotalWorldCases(worldTotalModel);
                    view.hideLoadingScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }

    @Override
    public void loadPhCases() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    view.displayLoadingScreen();
                    PhStatusModel phStatusModel = repository.fetchPhData();
                    view.displayTotalPhCases(phStatusModel);
                    view.hideLoadingScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }

    @Override
    public void loadSummaryCases() {
        
    }

    @Override
    public void loadPremiumData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PremiumTravelModel premiumTravelModel = repository.fetchPremiumData();
                    view.displayPremiumData(premiumTravelModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }

    @Override
    public void loadPhDailyData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<DailyPhStatusModel> dailyPhStatusModelList = repository.fetchPhDailyData();
                    view.displayPhDailyData(dailyPhStatusModelList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }
}
