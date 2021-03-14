package com.project.softeng2coronavirustrackerandroidapp.Adapters;

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
import java.util.ArrayList;
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
                int increase = getItem(position - 1).getInfected() - getItem(position).getInfected();
                int averageIncrease = computeAverageOfIncrease();
                if (increase > averageIncrease) {
                    holder.increaseGif.setVisibility(View.VISIBLE);
                    holder.decreaseGif.setVisibility(View.INVISIBLE);
                } else {
                    holder.increaseGif.setVisibility(View.INVISIBLE);
                    holder.decreaseGif.setVisibility(View.VISIBLE);
                }
                holder.txtInfectedIncrease.setText(String.valueOf(formatter.format(increase)));
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

    private int computeAverageOfIncrease() {
        int total = 0;
        int average = 0;
        for (int i = 0; i < list.size(); i++) {
            try {
                total += (list.get(i - 1).getInfected() - list.get(i).getInfected());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        average = total / list.size();
        return average;
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
        private LottieAnimationView increaseGif, decreaseGif;
        private Button btnSeeMore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalInfected = itemView.findViewById(R.id.txtTotalInfected);
            txtDate = itemView.findViewById(R.id.txtDate);
            increaseGif = itemView.findViewById(R.id.increase);
            btnSeeMore = itemView.findViewById(R.id.btnSeeMore);
            txtInfectedIncrease = itemView.findViewById(R.id.txtInfectedIncrease);
            decreaseGif = itemView.findViewById(R.id.decrease);
        }
    }
}
