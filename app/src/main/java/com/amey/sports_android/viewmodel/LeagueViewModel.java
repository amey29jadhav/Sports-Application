package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.repository.SportsApi;

import java.util.List;

public class LeagueViewModel extends AndroidViewModel {
    public LeagueViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Leagues>> getLeagueListObservable(SportsApi sportsApi) {
        return sportsApi.getAllLeague();

    }
    // TODO: Implement the ViewModel
}
