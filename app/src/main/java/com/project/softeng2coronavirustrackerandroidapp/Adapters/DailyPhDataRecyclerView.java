package com.project.softeng2coronavirustrackerandroidapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class DailyPhDataRecyclerView extends RecyclerView.Adapter<DailyPhDataRecyclerView.MyViewHolder> {

    private Context context;
    private List<DailyPhStatusModel> list;

    public DailyPhDataRecyclerView(Context context, List<DailyPhStatusModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_dailyphdata, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DailyPhStatusModel dailyPhStatusModel = getItem(position);
        NumberFormat formatter = new DecimalFormat("###,###,###");
        holder.txtTotalInfected.setText(String.valueOf(formatter.format(dailyPhStatusModel.getInfected())));
        holder.txtTotalTested.setText(dailyPhStatusModel.getTested());
        holder.txtTotalRecovered.setText(String.valueOf(formatter.format(dailyPhStatusModel.getRecovered())));
        holder.txtTotalDeceased.setText(String.valueOf(formatter.format(dailyPhStatusModel.getDeceased())));
        holder.txtTotalPuis.setText(String.valueOf(formatter.format(dailyPhStatusModel.getPersonUnderInvestigation())));
        holder.txtTotalPums.setText(String.valueOf(formatter.format(dailyPhStatusModel.getPersonUnderMonitoring())));
        holder.txtDate.setText(String.valueOf(dailyPhStatusModel.getDate()));
    }

    private DailyPhStatusModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTotalInfected, txtTotalTested, txtTotalRecovered, txtTotalDeceased, txtTotalPuis, txtTotalPums, txtDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalInfected = itemView.findViewById(R.id.txtTotalInfected);
            txtTotalTested = itemView.findViewById(R.id.txtTotalTested);
            txtTotalRecovered = itemView.findViewById(R.id.txtTotalRecovered);
            txtTotalDeceased = itemView.findViewById(R.id.txtTotalDeceased);
            txtTotalPuis = itemView.findViewById(R.id.txtTotalPuis);
            txtTotalPums = itemView.findViewById(R.id.txtTotalPums);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
