package com.project.softeng2coronavirustrackerandroidapp.Interfaces;

import com.project.softeng2coronavirustrackerandroidapp.Models.GlobalModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;
import java.util.List;

public interface IHomeContract {
    interface IHomeView {
        void displayLoadingScreen();

        void hideLoadingScreen();

        void displayTotalCases(WorldTotalModel globalModel);

        void displayPhStatus(List<PhStatusModel> phData);
    }

    interface IHomePresenter {
        void loadData();
    }

    interface IHomeRepository {
        List<PhStatusModel> fetchPhData() throws IOException;

        WorldTotalModel fetchWorldCases() throws IOException;

        GlobalModel fetchGlobalCases();
    }
}
