package com.project.softeng2coronavirustrackerandroidapp.Interfaces;

import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.MclTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;
import java.util.List;

public interface IHomeContract {
    interface IHomeView {
        void displayLoadingScreen();

        void hideLoadingScreen();

        void displayTotalWorldCases(WorldTotalModel globalModel);

        void displayTotalPhCases(PhStatusModel phStatusModel);

        void displayPhDailyData(List<DailyPhStatusModel> dailyPhStatusModels);

        void displayErrorFetchingDataMessageWorldCases();

        void displayErrorFetchingDataMessageTotalPhCases();

        void displayErrorFetchingPhDailyData();

        void displayProgressBarDailyPhData();

        void hideProgressBarDailyPhData();

        void displayProgressBarGlobalCases();

        void hideProgressBarGlobalCases();

        void displayProgressBarPhCases();

        void hideProgressBarPhCases();

        void displayProgressBarMclCases();

        void hideProgressBarMclCases();

        void displayErrorFetchingDataMessageMclCases();

        void displayMclCases(MclTotalModel mclTotalModel);
    }

    interface IHomePresenter {
        void loadWorldCases();

        void loadPhCases();

        void loadPhDailyData();

        void loadMclData();
    }

    interface IHomeRepository {
        PhStatusModel fetchPhData() throws IOException;

        WorldTotalModel fetchWorldCases() throws IOException;

        List<DailyPhStatusModel> fetchPhDailyData() throws IOException;

        MclTotalModel fetchMclTotalCases() throws IOException;
    }
}
