package com.project.softeng2coronavirustrackerandroidapp.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Presenter.HomePresenter;
import com.project.softeng2coronavirustrackerandroidapp.R;
import com.project.softeng2coronavirustrackerandroidapp.Repository.HomeRepository;

import java.util.List;

public class HomeFragment extends Fragment implements IHomeContract.IHomeView {
    private HomePresenter presenter;
    private TextView txt_sample;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        txt_sample = root.findViewById(R.id.sampleText);

        presenter = new HomePresenter(this, HomeRepository.getInstance());
        presenter.loadData();

        return root;
    }

    @Override
    public void displayLoadingScreen() {

    }

    @Override
    public void hideLoadingScreen() {

    }

    @Override
    public void displayPhStatus(List<PhStatusModel> phData) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt_sample.setText(phData.toString());
            }
        });
    }
}