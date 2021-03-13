package com.project.softeng2coronavirustrackerandroidapp.Views.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.project.softeng2coronavirustrackerandroidapp.Constants;
import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel.SummaryModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Presenter.HomePresenter;
import com.project.softeng2coronavirustrackerandroidapp.R;
import com.project.softeng2coronavirustrackerandroidapp.Repository.HomeRepository;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeContract.IHomeView {
    private HomePresenter presenter;
    private TextView txtTotalCases, txtTotalDeaths, txtTotalRecovered;
    private TextView txtInfectedPh, txtTestedPh, txtRecoveredPh, txtDeceasedPh, txtActiveCasesPh, txtUniquePh;//TODO: Add active and unique
    private LottieAnimationView lottieAnimationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initIds(root);
        presenter = new HomePresenter(this, HomeRepository.getInstance());
        presenter.loadWorldCases();
        presenter.loadPhCases();
        return root;
    }

    private void initIds(View root) {
        txtTotalCases = root.findViewById(R.id.txtTotalCases);
        txtTotalDeaths = root.findViewById(R.id.txtDeathCases);
        txtTotalRecovered = root.findViewById(R.id.txtRecovered);
        lottieAnimationView = root.findViewById(R.id.handwash);

        txtInfectedPh = root.findViewById(R.id.txtTotalCasesPH);
        txtTestedPh = root.findViewById(R.id.txtTestedPh);
        txtRecoveredPh = root.findViewById(R.id.txtRecoveredPH);
        txtDeceasedPh = root.findViewById(R.id.txtDeathCasesPH);
    }

    @Override
    public void displayLoadingScreen() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideLoadingScreen() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void displayTotalWorldCases(WorldTotalModel totalModel) {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (totalModel != null) {
                    NumberFormat formatter = new DecimalFormat("###,###,###");
                    txtTotalCases.setText(String.valueOf(formatter.format(totalModel.getTotalConfirmed())));
                    txtTotalDeaths.setText(String.valueOf(formatter.format(totalModel.getTotalDeaths())));
                    txtTotalRecovered.setText(String.valueOf(formatter.format(totalModel.getTotalRecovered())));
                } else {
                    txtTotalCases.setTextColor(Color.parseColor(Constants.COLOR_BLACK));
                    txtTotalDeaths.setTextColor(Color.parseColor(Constants.COLOR_BLACK));
                    txtTotalRecovered.setTextColor(Color.parseColor(Constants.COLOR_BLACK));

                    txtTotalCases.setText(Constants.SERVICE_UNAVAILABLE);
                    txtTotalDeaths.setText(Constants.SERVICE_UNAVAILABLE);
                    txtTotalRecovered.setText(Constants.SERVICE_UNAVAILABLE);
                }
            }
        });
    }

    @Override
    public void displayTotalPhCases(PhStatusModel phStatusModel) {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (phStatusModel != null) {
                    NumberFormat formatter = new DecimalFormat("###,###,###");
                    txtInfectedPh.setText(String.valueOf(formatter.format(phStatusModel.getInfected())));
                    txtTestedPh.setText(String.valueOf(formatter.format(phStatusModel.getTested())));
                    txtRecoveredPh.setText(String.valueOf(formatter.format(phStatusModel.getRecovered())));
                    txtDeceasedPh.setText(String.valueOf(formatter.format(phStatusModel.getDeceased())));
                } else {
                    txtInfectedPh.setText(Constants.SERVICE_UNAVAILABLE);
                    txtTestedPh.setText(Constants.SERVICE_UNAVAILABLE);
                    txtRecoveredPh.setText(Constants.SERVICE_UNAVAILABLE);
                    txtDeceasedPh.setText(Constants.SERVICE_UNAVAILABLE);
                }
            }
        });
    }

    @Override
    public void displaySummaryCases(SummaryModel summaryModel) {
        //TODO: Create this
    }
}