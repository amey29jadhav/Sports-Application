package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.service.repository.SportsApi;

import java.util.List;

public class UpComingViewModel extends AndroidViewModel {
    public UpComingViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<EventsModel.Events>> getEventsListObservable(SportsApi sportsApi, String teamId){
        return sportsApi.getUpcomingEvents(null, teamId);
    }
}
