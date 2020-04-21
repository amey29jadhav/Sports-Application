package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amey.sports_android.service.model.SeasonModel;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.service.repository.SportsApi;

import java.util.List;

public class SeasonViewModel extends AndroidViewModel {
    public SeasonViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<SeasonModel.Season>> getSeasonListObservable(SportsApi sportsApi, String leagueId){
        return sportsApi.getAllSeasons(null, leagueId);
    }
    // TODO: Implement the ViewModel
}
