package com.project.softeng2coronavirustrackerandroidapp.Views.Fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.project.softeng2coronavirustrackerandroidapp.Adapters.DailyPhDataRecyclerView;
import com.project.softeng2coronavirustrackerandroidapp.Constants;
import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel.PremiumTravelModel;
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
    private RecyclerView recyclerViewDailyPhData;
    private DailyPhDataRecyclerView adapter;
    private ProgressBar progressBarDailyPhData;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initIds(root);
        presenter = new HomePresenter(this, HomeRepository.getInstance());
        presenter.loadWorldCases();
        presenter.loadPhCases();
        presenter.loadPremiumData();
        presenter.loadPhDailyData();
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

        recyclerViewDailyPhData = root.findViewById(R.id.recyclerViewDailyPhData);
        progressBarDailyPhData = root.findViewById(R.id.progressBarDailyPhData);
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
                NumberFormat formatter = new DecimalFormat("###,###,###");
                txtTotalCases.setText(String.valueOf(formatter.format(totalModel.getTotalConfirmed())));
                txtTotalDeaths.setText(String.valueOf(formatter.format(totalModel.getTotalDeaths())));
                txtTotalRecovered.setText(String.valueOf(formatter.format(totalModel.getTotalRecovered())));
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
                NumberFormat formatter = new DecimalFormat("###,###,###");
                txtInfectedPh.setText(String.valueOf(formatter.format(phStatusModel.getInfected())));
                txtTestedPh.setText(String.valueOf(formatter.format(phStatusModel.getTested())));
                txtRecoveredPh.setText(String.valueOf(formatter.format(phStatusModel.getRecovered())));
                txtDeceasedPh.setText(String.valueOf(formatter.format(phStatusModel.getDeceased())));
            }
        });
    }

    @Override
    public void displayPremiumData(PremiumTravelModel premiumTravelModel) {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        });
    }

    @Override
    public void displayPhDailyData(List<DailyPhStatusModel> dailyPhStatusModels) {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(HomeFragment.this.getActivity(), LinearLayoutManager.VERTICAL, false);
                adapter = new DailyPhDataRecyclerView(HomeFragment.this.getActivity(), dailyPhStatusModels);
                recyclerViewDailyPhData.setLayoutManager(layoutManager);
                recyclerViewDailyPhData.setAdapter(adapter);
                recyclerViewDailyPhData.scheduleLayoutAnimation();

                adapter.setOnRecyclerViewSeeMoreClickListener(new DailyPhDataRecyclerView.OnRecyclerViewClickListener() {
                    @Override
                    public void onClick(DailyPhStatusModel dailyPhStatusModel) {
                        displayPopupDialog(dailyPhStatusModel);
                    }
                });
            }
        });
    }

    private void displayPopupDialog(DailyPhStatusModel dailyPhStatusModels) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeFragment.this.getActivity());
        if (HomeFragment.this.getActivity() == null)
            return;
        LayoutInflater inflater = HomeFragment.this.getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_dailyphdata, null);
        Button btnGotoSource = dialogView.findViewById(R.id.btnGotoSource);

        TextView txtTestedPh, txtRecoveredPh, txtDeceasedPh;
        txtTestedPh = dialogView.findViewById(R.id.txtTestedPh);
        txtRecoveredPh = dialogView.findViewById(R.id.txtRecoveredPh);
        txtDeceasedPh = dialogView.findViewById(R.id.txtDeceasedPh);
        TextView txtPuis = dialogView.findViewById(R.id.txtPUIPh);
        TextView txtPums = dialogView.findViewById(R.id.txtPUMPh);

        NumberFormat formatter = new DecimalFormat("###,###,###");
        if (!dailyPhStatusModels.getTested().equals("N/A")) {
            txtTestedPh.setText(String.valueOf(formatter.format(Integer.parseInt(dailyPhStatusModels.getTested()))));
            txtTestedPh.setTextColor(Color.BLUE);
        }
        txtRecoveredPh.setText(String.valueOf(formatter.format(dailyPhStatusModels.getRecovered())));
        txtRecoveredPh.setTextColor(Color.GREEN);
        txtDeceasedPh.setText(String.valueOf(formatter.format(dailyPhStatusModels.getDeceased())));
        txtDeceasedPh.setTextColor(Color.RED);
        txtPuis.setText(String.valueOf(formatter.format(dailyPhStatusModels.getPersonUnderInvestigation())));
        txtPums.setText(String.valueOf(formatter.format(dailyPhStatusModels.getPersonUnderMonitoring())));
        btnGotoSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeFragment.this.getActivity(), "Function not available now", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.show();
    }

    @Override
    public void displayErrorFetchingDataMessageWorldCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtTotalCases.setTextColor(Color.parseColor(Constants.COLOR_BLACK));
                txtTotalDeaths.setTextColor(Color.parseColor(Constants.COLOR_BLACK));
                txtTotalRecovered.setTextColor(Color.parseColor(Constants.COLOR_BLACK));

                txtTotalCases.setText(Constants.SERVICE_UNAVAILABLE);
                txtTotalDeaths.setText(Constants.SERVICE_UNAVAILABLE);
                txtTotalRecovered.setText(Constants.SERVICE_UNAVAILABLE);

                Snackbar.make(root, Constants.SERVICE_UNAVAILABLE, Snackbar.LENGTH_LONG)
                        .setTextColor(Color.BLACK)
                        .setBackgroundTint(Color.parseColor(Constants.COLOR_PRIMARY));
            }
        });
    }

    @Override
    public void displayErrorFetchingDataMessageTotalPhCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtInfectedPh.setText(Constants.SERVICE_UNAVAILABLE);
                txtTestedPh.setText(Constants.SERVICE_UNAVAILABLE);
                txtRecoveredPh.setText(Constants.SERVICE_UNAVAILABLE);
                txtDeceasedPh.setText(Constants.SERVICE_UNAVAILABLE);

                Snackbar.make(root, Constants.SERVICE_UNAVAILABLE, Snackbar.LENGTH_LONG)
                        .setTextColor(Color.BLACK)
                        .setBackgroundTint(Color.parseColor(Constants.COLOR_PRIMARY));
            }
        });
    }

    @Override
    public void displayErrorFetchingPhDailyData() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(root, Constants.SERVICE_UNAVAILABLE, Snackbar.LENGTH_LONG)
                        .setTextColor(Color.BLACK)
                        .setBackgroundTint(Color.parseColor(Constants.COLOR_PRIMARY));
            }
        });
    }

    @Override
    public void displayProgressBarDailyPhData() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarDailyPhData.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgressBarDailyPhData() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarDailyPhData.setVisibility(View.GONE);
            }
        });
    }
}