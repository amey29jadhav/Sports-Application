package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.LastEventModel;
import com.amey.sports_android.service.repository.SportsApi;

import java.util.List;

public class PastEventViewModel extends AndroidViewModel {
    public PastEventViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<LastEventModel.Events>> getLastEventsListObservable(SportsApi sportsApi, String teamId){
        return sportsApi.getLastEvents(null, teamId);
    }
}
