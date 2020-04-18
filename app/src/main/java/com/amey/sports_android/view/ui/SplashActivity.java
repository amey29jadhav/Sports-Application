package com.amey.sports_android.view.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.SportsModel;
import com.amey.sports_android.service.repository.GameService;
import com.amey.sports_android.service.repository.RetrofitClientInstance;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.utilities.AppConstant;
import com.amey.sports_android.view.callback.ResultInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
            //SportsApi.newInstance().getAllTeams();

    }
}
