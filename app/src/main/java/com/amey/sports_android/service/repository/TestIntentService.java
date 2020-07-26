package com.amey.sports_android.service.repository;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class TestIntentService extends IntentService {

    public TestIntentService(String name){
        super(name);

    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println();
    }
}
