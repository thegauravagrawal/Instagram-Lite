package com.inficreations.instagramlite.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.inficreations.instagramlite.R;
import com.inficreations.instagramlite.model.AppConstants;
import com.inficreations.instagramlite.model.SharedPreferencesLocalDB;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide the status bar and navigation bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        //
        SharedPreferencesLocalDB.getUserData(this);
        System.out.println("USER ID: " + AppConstants.USER_PK_ID);

        //
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (AppConstants.USER_PK_ID == 0) {
                            //Open Login Screen
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        } else {
                            //Open Home Screen
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        }
                        finish();
                    }
                }, 2000);
    }
}
