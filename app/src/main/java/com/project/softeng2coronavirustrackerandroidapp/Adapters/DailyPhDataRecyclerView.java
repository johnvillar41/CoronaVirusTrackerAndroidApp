package com.project.softeng2coronavirustrackerandroidapp.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.card.MaterialCardView;
import com.project.softeng2coronavirustrackerandroidapp.Models.DailyPhStatusModel;
import com.project.softeng2coronavirustrackerandroidapp.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class DailyPhDataRecyclerView extends RecyclerView.Adapter<DailyPhDataRecyclerView.MyViewHolder> {

    private List<DailyPhStatusModel> list;
    private Context context;
    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener {
        void OnClickListener(int position, List<DailyPhStatusModel> dailyPhStatusModels);
    }

    public void setOnRecyclerViewSeeMoreClick(OnRecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public DailyPhDataRecyclerView(Context context, List<DailyPhStatusModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_dailyphdata, parent, false);
        return new MyViewHolder(view, listener, list);
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
                    holder.txtInfectedIncrease.setTextColor(Color.RED);
                    holder.increaseGif.setVisibility(View.VISIBLE);
                    holder.decreaseGif.setVisibility(View.INVISIBLE);
                } else {
                    holder.txtInfectedIncrease.setTextColor(Color.GREEN);
                    holder.increaseGif.setVisibility(View.INVISIBLE);
                    holder.decreaseGif.setVisibility(View.VISIBLE);
                }
                holder.txtInfectedIncrease.setText(String.valueOf(formatter.format(increase)));
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        holder.txtTotalInfected.setText(String.valueOf(formatter.format(dailyPhStatusModel.getInfected())));
        holder.txtDate.setText(String.valueOf(dailyPhStatusModel.getDate()));
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

        public MyViewHolder(@NonNull View itemView, OnRecyclerViewClickListener listener, List<DailyPhStatusModel> list) {
            super(itemView);
            txtTotalInfected = itemView.findViewById(R.id.txtTotalInfected);
            txtDate = itemView.findViewById(R.id.txtDate);
            increaseGif = itemView.findViewById(R.id.increase);
            btnSeeMore = itemView.findViewById(R.id.btnSeeMore);
            txtInfectedIncrease = itemView.findViewById(R.id.txtInfectedIncrease);
            decreaseGif = itemView.findViewById(R.id.decrease);

            btnSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.OnClickListener(position, list);
                }
            });
        }
    }
}
