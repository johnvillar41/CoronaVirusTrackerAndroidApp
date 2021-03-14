package com.project.softeng2coronavirustrackerandroidapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class DailyPhDataRecyclerView extends RecyclerView.Adapter<DailyPhDataRecyclerView.MyViewHolder> {

    private List<DailyPhStatusModel> list;

    public DailyPhDataRecyclerView(List<DailyPhStatusModel> list) {
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

        try {
            if (getItem(position).getInfected() < getItem(position - 1).getInfected()) {
                holder.lottieAnimationView.setVisibility(View.VISIBLE);
                int increase = getItem(position - 1).getInfected() - getItem(position).getInfected();
                holder.txtInfectedIncrease.setText(String.valueOf(increase));
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        holder.txtTotalInfected.setText(String.valueOf(formatter.format(dailyPhStatusModel.getInfected())));
        holder.txtDate.setText(String.valueOf(dailyPhStatusModel.getDate()));
        holder.btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private DailyPhStatusModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTotalInfected, txtDate, txtInfectedIncrease;
        private LottieAnimationView lottieAnimationView;
        private Button btnSeeMore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalInfected = itemView.findViewById(R.id.txtTotalInfected);
            txtDate = itemView.findViewById(R.id.txtDate);
            lottieAnimationView = itemView.findViewById(R.id.increase);
            btnSeeMore = itemView.findViewById(R.id.btnSeeMore);
            txtInfectedIncrease = itemView.findViewById(R.id.txtInfectedIncrease);
        }
    }
}
