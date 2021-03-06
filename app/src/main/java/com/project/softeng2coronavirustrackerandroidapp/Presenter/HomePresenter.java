package com.project.softeng2coronavirustrackerandroidapp.Presenter;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.MclTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
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
                    view.displayProgressBarGlobalCases();
                    WorldTotalModel worldTotalModel = repository.fetchWorldCases();
                    if (worldTotalModel == null) {
                        view.displayErrorFetchingDataMessageWorldCases();
                        return;
                    }
                    view.displayTotalWorldCases(worldTotalModel);
                    view.hideProgressBarGlobalCases();
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
                    view.displayProgressBarPhCases();
                    PhStatusModel phStatusModel = repository.fetchPhData();
                    if (phStatusModel == null) {
                        view.displayErrorFetchingPhDailyData();
                        return;
                    }
                    view.displayTotalPhCases(phStatusModel);
                    view.hideProgressBarPhCases();
                    view.hideLoadingScreen();
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
                    view.displayLoadingScreen();
                    view.displayProgressBarDailyPhData();
                    List<DailyPhStatusModel> dailyPhStatusModelList = repository.fetchPhDailyData();
                    if (dailyPhStatusModelList == null) {
                        view.displayErrorFetchingPhDailyData();
                        return;
                    }
                    view.displayPhDailyData(dailyPhStatusModelList);
                    view.hideProgressBarDailyPhData();
                    view.hideLoadingScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void loadMclData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    view.displayLoadingScreen();
                    view.displayProgressBarMclCases();
                    MclTotalModel mclTotalModel = repository.fetchMclTotalCases();
                    if (mclTotalModel == null) {
                        view.displayErrorFetchingDataMessageMclCases();
                        return;
                    }
                    view.displayMclCases(mclTotalModel);
                    view.hideProgressBarMclCases();
                    view.hideLoadingScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }
}
