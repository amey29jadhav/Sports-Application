package com.amey.sports_android.service.repository;

import android.app.IntentService;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class TestJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        for(int i = 0; i < 1000; i++){
            System.out.println("----onStartJob"+i);
        }
        System.out.println("---------Job Finished----------");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
