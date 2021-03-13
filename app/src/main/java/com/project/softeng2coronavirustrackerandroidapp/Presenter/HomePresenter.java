package com.project.softeng2coronavirustrackerandroidapp.Presenter;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;

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
    public void loadData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<PhStatusModel> phstatusList = repository.fetchPhData();
                    view.displayPhStatus(phstatusList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }
}
