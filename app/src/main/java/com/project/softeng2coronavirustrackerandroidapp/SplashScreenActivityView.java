package com.project.softeng2coronavirustrackerandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreenActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideStatusBar.hideStatusBar(this);
        setContentView(R.layout.activity_splash_screen);
    }
}