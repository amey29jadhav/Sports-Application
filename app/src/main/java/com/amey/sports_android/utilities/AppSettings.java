package com.amey.sports_android.utilities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.amey.sports_android.service.repository.TestIntentService;
import com.amey.sports_android.service.repository.TestJobService;

public class AppSettings {

    public static JobInfo.Builder builder;

    public static void scheduleJob(Context context){
        ComponentName serviceComponent = new ComponentName(context, TestJobService.class);
         builder = new JobInfo.Builder(0, serviceComponent);
         builder.setMinimumLatency(1 * 1000); // wait at least
         builder.setOverrideDeadline(3 * 1000); // maximum delay


        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }

    public static void enquetask(Context context){
        Intent startJsonDownloadService = new Intent(context, TestIntentService.class);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        //jobScheduler.enqueue(builder,new JobWorkItem(startJsonDownloadService));
    }


}
