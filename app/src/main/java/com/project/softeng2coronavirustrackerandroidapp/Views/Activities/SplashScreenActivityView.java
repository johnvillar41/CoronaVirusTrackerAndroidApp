package com.project.softeng2coronavirustrackerandroidapp.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.project.softeng2coronavirustrackerandroidapp.HideStatusBar;
import com.project.softeng2coronavirustrackerandroidapp.R;

public class SplashScreenActivityView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideStatusBar.hideStatusBar(this);
        setContentView(R.layout.activity_splash_screen);

        Button btnNavigateToStart = findViewById(R.id.btnNavigateToStart);
        btnNavigateToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreenActivityView.this, MainActivityActivityView.class);
                startActivity(intent);
                SplashScreenActivityView.this.finish();
            }
        });
    }
}