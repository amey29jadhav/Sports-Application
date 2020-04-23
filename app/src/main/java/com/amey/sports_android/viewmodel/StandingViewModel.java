package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.Standing;
import com.amey.sports_android.service.model.StandingModel;
import com.amey.sports_android.service.repository.SportsApi;

import java.util.List;

public class StandingViewModel extends AndroidViewModel {
    public StandingViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Standing>> getStandingsListObservable(SportsApi sportsApi, String leagueId, String seasonId){
        return sportsApi.getAllStanding(null, leagueId,seasonId);
    }
    // TODO: Implement the ViewModel
}
