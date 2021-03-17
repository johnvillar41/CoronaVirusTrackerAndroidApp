package com.project.softeng2coronavirustrackerandroidapp;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.MclTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Presenter.HomePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HomePresenterTest {
    private IHomeContract.IHomeView view;
    private IHomeContract.IHomeRepository repository;
    private IHomeContract.IHomePresenter presenter;

    @Before
    public void setUp() {
        view = new MockHomeView();
        repository = new MockHomeRepository();
        presenter = new HomePresenter(view, repository);
    }

    @Test
    public void shouldPassDisplayingTotalWorldCases() throws InterruptedException {
        presenter.loadWorldCases();
        Thread.sleep(1000);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenShowing);
        Assert.assertTrue(((MockHomeView) view).isProgressBarShowing);
        Assert.assertTrue(((MockHomeView) view).isDataPassed);
        Assert.assertTrue(((MockHomeView) view).isProgressBarHidden);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenHidden);
    }

    @Test
    public void shouldPassDisplayingTotalPhCases() throws InterruptedException {
        presenter.loadPhCases();
        Thread.sleep(1000);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenShowing);
        Assert.assertTrue(((MockHomeView) view).isProgressBarShowing);
        Assert.assertTrue(((MockHomeView) view).isDataPassed);
        Assert.assertTrue(((MockHomeView) view).isProgressBarHidden);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenHidden);
    }

    @Test
    public void shouldPassDisplayingPhDailyData() throws InterruptedException {
        presenter.loadPhDailyData();
        Thread.sleep(1000);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenShowing);
        Assert.assertTrue(((MockHomeView) view).isProgressBarShowing);
        Assert.assertTrue(((MockHomeView) view).isDataPassed);
        Assert.assertTrue(((MockHomeView) view).isProgressBarHidden);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenHidden);
    }

    @Test
    public void shouldPassDisplayMclTotalData() throws InterruptedException {
        presenter.loadMclData();
        Thread.sleep(1000);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenShowing);
        Assert.assertTrue(((MockHomeView) view).isProgressBarShowing);
        Assert.assertTrue(((MockHomeView) view).isDataPassed);
        Assert.assertTrue(((MockHomeView) view).isProgressBarHidden);
        Assert.assertTrue(((MockHomeView) view).isLoadingScreenHidden);
    }


    private static class MockHomeView implements IHomeContract.IHomeView {
        boolean isDataPassed = false;
        boolean isLoadingScreenShowing = false;
        boolean isLoadingScreenHidden = false;
        boolean isProgressBarShowing = false;
        boolean isProgressBarHidden = false;

        @Override
        public void displayLoadingScreen() {
            isLoadingScreenShowing = true;
        }

        @Override
        public void hideLoadingScreen() {
            isLoadingScreenHidden = true;
        }

        @Override
        public void displayTotalWorldCases(WorldTotalModel globalModel) {
            if (globalModel != null)
                isDataPassed = true;

        }

        @Override
        public void displayTotalPhCases(PhStatusModel phStatusModel) {
            if (phStatusModel != null)
                isDataPassed = true;
        }

        @Override
        public void displayPhDailyData(List<DailyPhStatusModel> dailyPhStatusModels) {
            if (dailyPhStatusModels != null)
                isDataPassed = true;
        }

        @Override
        public void displayErrorFetchingDataMessageWorldCases() {

        }

        @Override
        public void displayErrorFetchingDataMessageTotalPhCases() {

        }

        @Override
        public void displayErrorFetchingPhDailyData() {

        }

        @Override
        public void displayProgressBarDailyPhData() {
            isProgressBarShowing = true;
        }

        @Override
        public void hideProgressBarDailyPhData() {
            isProgressBarHidden = true;
        }

        @Override
        public void displayProgressBarGlobalCases() {
            isProgressBarShowing = true;
        }

        @Override
        public void hideProgressBarGlobalCases() {
            isProgressBarHidden = true;
        }

        @Override
        public void displayProgressBarPhCases() {
            isProgressBarShowing = true;
        }

        @Override
        public void hideProgressBarPhCases() {
            isProgressBarHidden = true;
        }

        @Override
        public void displayProgressBarMclCases() {
            isProgressBarShowing = true;
        }

        @Override
        public void hideProgressBarMclCases() {
            isProgressBarHidden = true;
        }

        @Override
        public void displayErrorFetchingDataMessageMclCases() {

        }

        @Override
        public void displayMclCases(MclTotalModel mclTotalModel) {
            if (mclTotalModel != null)
                isDataPassed = true;
        }
    }

    private static class MockHomeRepository implements IHomeContract.IHomeRepository {

        @Override
        public PhStatusModel fetchPhData() {
            return new PhStatusModel(0, 0, 0, 0, 0, 0);
        }

        @Override
        public WorldTotalModel fetchWorldCases() {
            return new WorldTotalModel(0, 0, 0);
        }

        @Override
        public List<DailyPhStatusModel> fetchPhDailyData() {
            return new ArrayList<DailyPhStatusModel>();
        }

        @Override
        public MclTotalModel fetchMclTotalCases() {
            return new MclTotalModel(1, 1, 1, 1);
        }
    }
}