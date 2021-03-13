package com.project.softeng2coronavirustrackerandroidapp.Views.Fragments;

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
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Presenter.HomePresenter;
import com.project.softeng2coronavirustrackerandroidapp.R;
import com.project.softeng2coronavirustrackerandroidapp.Repository.HomeRepository;

import java.util.List;

public class HomeFragment extends Fragment implements IHomeContract.IHomeView {
    private HomePresenter presenter;
    private TextView txtTotalCases, txtTotalDeaths, txtTotalRecovered;
    private LottieAnimationView lottieAnimationView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        txtTotalCases = root.findViewById(R.id.txtTotalCases);
        txtTotalDeaths = root.findViewById(R.id.txtDeathCases);
        txtTotalRecovered = root.findViewById(R.id.txtRecovered);
        lottieAnimationView = root.findViewById(R.id.handwash);
        presenter = new HomePresenter(this, HomeRepository.getInstance());
        presenter.loadData();
        return root;
    }

    @Override
    public void displayLoadingScreen() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideLoadingScreen() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void displayTotalCases(WorldTotalModel totalModel) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (totalModel != null) {
                    txtTotalCases.setText(totalModel.getTotalConfirmed());
                    txtTotalDeaths.setText(totalModel.getTotalDeaths());
                    txtTotalRecovered.setText(totalModel.getTotalRecovered());
                } else {
                    txtTotalCases.setText(Constants.SERVICE_UNAVAILABLE);
                    txtTotalDeaths.setText(Constants.SERVICE_UNAVAILABLE);
                    txtTotalRecovered.setText(Constants.SERVICE_UNAVAILABLE);
                }
            }
        });
    }

    @Override
    public void displayPhStatus(List<PhStatusModel> phData) {

    }
}