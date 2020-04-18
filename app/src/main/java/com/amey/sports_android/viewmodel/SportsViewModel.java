package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.repository.SportsApi;

import java.util.List;

public class SportsViewModel extends AndroidViewModel {


    public SportsViewModel(@NonNull Application application) {
        super(application);
    }

    // TODO: Implement the ViewModel

    public LiveData<List<Sports>> getSportsListObservable(SportsApi sportsApi) {
        return sportsApi.getAllTeams();

    }
}
