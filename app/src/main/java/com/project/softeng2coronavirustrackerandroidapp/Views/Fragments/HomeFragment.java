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
import com.project.softeng2coronavirustrackerandroidapp.Models.SummaryModel;
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
    private LottieAnimationView lottieAnimationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initIds(root);
        presenter = new HomePresenter(this, HomeRepository.getInstance());
        presenter.loadWorldCases();
        return root;
    }

    private void initIds(View root) {
        txtTotalCases = root.findViewById(R.id.txtTotalCases);
        txtTotalDeaths = root.findViewById(R.id.txtDeathCases);
        txtTotalRecovered = root.findViewById(R.id.txtRecovered);
        lottieAnimationView = root.findViewById(R.id.handwash);
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
    public void displayTotalCases(WorldTotalModel totalModel) {
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
    public void displaySummaryCases(SummaryModel summaryModel) {
        //TODO: Create this
    }

    @Override
    public void displayPhStatus(List<PhStatusModel> phData) {
        //TODO: Create this
    }
}