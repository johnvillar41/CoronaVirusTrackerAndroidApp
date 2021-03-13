package com.project.softeng2coronavirustrackerandroidapp.Interfaces;

import com.project.softeng2coronavirustrackerandroidapp.Models.GlobalCasesModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;
import java.util.List;

public interface IHomeContract {
    interface IHomeView {
        void displayLoadingScreen();

        void hideLoadingScreen();

        void displayTotalCases(WorldTotalModel globalModel);

        void displaySummaryCases(SummaryModel summaryModel);

        void displayPhStatus(List<PhStatusModel> phData);
    }

    interface IHomePresenter {
        void loadWorldCases();

        void loadSummaryCases();
    }

    interface IHomeRepository {
        List<PhStatusModel> fetchPhData() throws IOException;

        WorldTotalModel fetchWorldCases() throws IOException;

        GlobalCasesModel fetchGlobalCases();

        SummaryModel fetchSummaryCases() throws IOException;
    }
}
