package com.project.softeng2coronavirustrackerandroidapp.Presenter;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;

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
                    view.displayTotalCases(worldTotalModel);
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
}
