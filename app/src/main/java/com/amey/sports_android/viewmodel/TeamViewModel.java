package com.amey.sports_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amey.sports_android.service.model.TeamModel;

import java.util.List;

public class TeamViewModel extends AndroidViewModel {
    private  LiveData<List<TeamModel>> TeamListObservable;

    public TeamViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<TeamModel>> getTeamListObservable(){
        return TeamListObservable;
    }

    // TODO: Implement the ViewModel
}
