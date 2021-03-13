package com.project.softeng2coronavirustrackerandroidapp.Views.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.softeng2coronavirustrackerandroidapp.HideStatusBar;
import com.project.softeng2coronavirustrackerandroidapp.R;
import com.project.softeng2coronavirustrackerandroidapp.Views.Fragments.DashboardFragment;
import com.project.softeng2coronavirustrackerandroidapp.Views.Fragments.HomeFragment;
import com.project.softeng2coronavirustrackerandroidapp.Views.Fragments.NotificationsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideStatusBar.hideStatusBar(this);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean isClicked = false;
        Fragment fragment;
        if (item.getItemId() == R.id.navigation_home) {
            fragment = new HomeFragment();
            loadFragment(fragment);
            isClicked = true;
        } else if (item.getItemId() == R.id.navigation_dashboard) {
            fragment = new DashboardFragment();
            loadFragment(fragment);
            isClicked = true;
        } else if (item.getItemId() == R.id.navigation_notifications) {
            fragment = new NotificationsFragment();
            loadFragment(fragment);
            isClicked = true;
        }
        return isClicked;
    }

    private void loadFragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment,fragment)
                    .commit();
        }

    }
}