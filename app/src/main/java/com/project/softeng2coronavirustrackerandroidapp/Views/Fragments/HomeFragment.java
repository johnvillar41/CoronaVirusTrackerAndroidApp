package com.project.softeng2coronavirustrackerandroidapp.Views.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.project.softeng2coronavirustrackerandroidapp.Adapters.DailyPhDataRecyclerView;
import com.project.softeng2coronavirustrackerandroidapp.Constants;
import com.project.softeng2coronavirustrackerandroidapp.Interfaces.IHomeContract;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.MclTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.PremiumTravelModel.PremiumTravelModel;
import com.project.softeng2coronavirustrackerandroidapp.Models.WorldTotalModel;
import com.project.softeng2coronavirustrackerandroidapp.Presenter.HomePresenter;
import com.project.softeng2coronavirustrackerandroidapp.R;
import com.project.softeng2coronavirustrackerandroidapp.Repository.HomeRepository;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeContract.IHomeView {

    private HomePresenter presenter;
    private TextView txtTotalCases, txtTotalDeaths, txtTotalRecovered;
    private TextView txtInfectedPh, txtTestedPh, txtRecoveredPh, txtDeceasedPh;
    private TextView txtInfectedMcl, txtTestedMcl, txtRecoveredMcl, txtDeceasedMcl;
    private RecyclerView recyclerViewDailyPhData;
    private DailyPhDataRecyclerView adapter;
    private ProgressBar progressBarDailyPhData, progressBarGlobalCases, progressBarPhCases, progressBarMclCases, progressBarHomeFragment;
    private int progressBarCounter = 0;
    private Button btnSearchByDate, btnSeeAllDailyPhData;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initIds(root);
        presenter = new HomePresenter(this, HomeRepository.getInstance());
        presenter.loadWorldCases();
        presenter.loadPhCases();
        presenter.loadPhDailyData();
        return root;
    }

    @Override
    public void displayLoadingScreen() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarHomeFragment.setVisibility(View.VISIBLE);
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
                if (progressBarCounter == 4) {
                    progressBarHomeFragment.setVisibility(View.INVISIBLE);
                }
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
                progressBarCounter++;
            }
        });
    }

    @Override
    public void displayProgressBarGlobalCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarGlobalCases.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgressBarGlobalCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarGlobalCases.setVisibility(View.INVISIBLE);
                progressBarCounter++;
            }
        });

    }

    @Override
    public void displayProgressBarPhCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarPhCases.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideProgressBarPhCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarPhCases.setVisibility(View.INVISIBLE);
                progressBarCounter++;
            }
        });

    }

    @Override
    public void displayProgressBarMclCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarMclCases.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideProgressBarMclCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarCounter++;
                progressBarMclCases.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void displayErrorFetchingDataMessageMclCases() {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void displayMclCases(MclTotalModel mclTotalModel) {
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NumberFormat formatter = new DecimalFormat("###,###,###");
                txtInfectedMcl.setText(String.valueOf(formatter.format(mclTotalModel.getTotalConfirmed())));
                txtTestedMcl.setText(String.valueOf(formatter.format(mclTotalModel.getTotalTested())));
                txtRecoveredMcl.setText(String.valueOf(formatter.format(mclTotalModel.getTotalRecovered())));
                txtDeceasedMcl.setText(String.valueOf(formatter.format(mclTotalModel.getTotalDeaths())));
            }
        });
    }

    private void initIds(View root) {
        txtTotalCases = root.findViewById(R.id.txtTotalCases);
        txtTotalDeaths = root.findViewById(R.id.txtDeathCases);
        txtTotalRecovered = root.findViewById(R.id.txtRecovered);

        txtInfectedPh = root.findViewById(R.id.txtTotalCasesPH);
        txtTestedPh = root.findViewById(R.id.txtTestedPh);
        txtRecoveredPh = root.findViewById(R.id.txtRecoveredPH);
        txtDeceasedPh = root.findViewById(R.id.txtDeathCasesPH);

        txtInfectedMcl = root.findViewById(R.id.txtTotalCasesMCL);
        txtTestedMcl = root.findViewById(R.id.txtTestedMCL);
        txtRecoveredMcl = root.findViewById(R.id.txtRecoveredMCL);
        txtDeceasedMcl = root.findViewById(R.id.txtDeathCasesMCL);

        recyclerViewDailyPhData = root.findViewById(R.id.recyclerViewDailyPhData);
        progressBarDailyPhData = root.findViewById(R.id.progressBarDailyPhData);
        btnSearchByDate = root.findViewById(R.id.btnSearchByDate);
        btnSeeAllDailyPhData = root.findViewById(R.id.btnSeeAllDailyPhData);
        progressBarGlobalCases = root.findViewById(R.id.progressBarGlobalCases);
        progressBarPhCases = root.findViewById(R.id.progressBarPhCases);
        progressBarMclCases = root.findViewById(R.id.progressBarMclCases);
        progressBarHomeFragment = root.findViewById(R.id.progressBarHome);
        btnSearchByDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalendar();
            }
        });
        btnSeeAllDailyPhData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getFilter().filter(null);
            }
        });
    }

    private void displayCalendar() {
        DatePickerDialog datePicker;
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker = new DatePickerDialog(HomeFragment.this.getActivity(), new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(year, month, dayOfMonth);
                String dateString = sdf.format(calendar.getTime());
                adapter.getFilter().filter(dateString);
            }
        }, year, month, day);
        datePicker.show();
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
}