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
                    if (worldTotalModel == null)
                        view.displayErrorFetchingDataMessageWorldCases();
                    view.displayTotalWorldCases(worldTotalModel);
                    view.hideLoadingScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void loadPhCases() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    view.displayLoadingScreen();
                    PhStatusModel phStatusModel = repository.fetchPhData();
                    if (phStatusModel == null)
                        view.displayErrorFetchingPhDailyData();
                    view.displayTotalPhCases(phStatusModel);
                    view.hideLoadingScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void loadSummaryCases() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO:
            }
        });thread.start();
    }

    @Override
    public void loadPremiumData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //TODO:
                    PremiumTravelModel premiumTravelModel = repository.fetchPremiumData();
                    view.displayPremiumData(premiumTravelModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void loadPhDailyData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    view.displayProgressBarDailyPhData();
                    List<DailyPhStatusModel> dailyPhStatusModelList = repository.fetchPhDailyData();
                    if (dailyPhStatusModelList == null)
                        view.displayErrorFetchingPhDailyData();
                    view.displayPhDailyData(dailyPhStatusModelList);
                    view.hideProgressBarDailyPhData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
