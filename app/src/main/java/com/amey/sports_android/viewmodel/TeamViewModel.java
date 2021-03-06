package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.service.repository.SportsApi;

import java.util.List;

public class TeamViewModel extends AndroidViewModel {

    public TeamViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<TeamModel.Team>> getTeamListObservable(SportsApi sportsApi, String leagueName){
        return sportsApi.getAllTeams(null, leagueName);
    }

    // TODO: Implement the ViewModel
}
