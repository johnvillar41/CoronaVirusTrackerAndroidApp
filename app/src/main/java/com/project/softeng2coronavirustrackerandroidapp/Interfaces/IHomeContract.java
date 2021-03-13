package com.project.softeng2coronavirustrackerandroidapp.Interfaces;

import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel.GlobalCasesModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel.SummaryModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;

import java.io.IOException;
import java.util.List;

public interface IHomeContract {
    interface IHomeView {
        void displayLoadingScreen();

        void hideLoadingScreen();

        void displayTotalWorldCases(WorldTotalModel globalModel);

        void displayTotalPhCases(PhStatusModel phStatusModel);

        void displaySummaryCases(SummaryModel summaryModel);
    }

    interface IHomePresenter {
        void loadWorldCases();

        void loadPhCases();

        void loadSummaryCases();
    }

    interface IHomeRepository {
        PhStatusModel fetchPhData() throws IOException;

        WorldTotalModel fetchWorldCases() throws IOException;

        GlobalCasesModel fetchGlobalCases();

        SummaryModel fetchSummaryCases() throws IOException;
    }
}
