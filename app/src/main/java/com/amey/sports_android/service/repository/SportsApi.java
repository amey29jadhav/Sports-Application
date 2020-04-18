package com.amey.sports_android.service.repository;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.LeaguesModel;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.SportsModel;
import com.amey.sports_android.utilities.AppConstant;
import com.amey.sports_android.view.callback.ResultInterface;
import com.amey.sports_android.view.ui.MainActivity;
import com.amey.sports_android.view.ui.SplashActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsApi {

    private static SportsApi sportsApi;

    public static SportsApi newInstance(){
        if(sportsApi == null){
            sportsApi = new SportsApi();
        }
        return sportsApi;
    }

    public LiveData<List<Sports>> getAllTeams(){
        final MutableLiveData<List<Sports>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<SportsModel> listSport = gameService.getAllSports(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.GET_ALL_SPORTS);
        listSport.enqueue(new Callback<SportsModel>() {
            @Override
            public void onResponse(Call<SportsModel> call, Response<SportsModel> response) {
                if(response != null){
                    SportsModel sportsModel =response.body();
                    if(sportsModel.sports != null){
                        data.setValue(sportsModel.sports);
                    }

                }
            }

            @Override
            public void onFailure(Call<SportsModel> call, Throwable t) {
                    data.setValue(null);

            }
        });
        return data;
    }

    public LiveData<List<Leagues>> getAllLeague(){
        final MutableLiveData<List<Leagues>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<LeaguesModel> listSport = gameService.getAllLeagues(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.GET_ALL_LEAGUES);
        listSport.enqueue(new Callback<LeaguesModel>() {
            @Override
            public void onResponse(Call<LeaguesModel> call, Response<LeaguesModel> response) {
                if(response != null){
                    LeaguesModel leaguesModel =response.body();
                    if(leaguesModel.leagues != null){
                        data.setValue(leaguesModel.leagues);

                    }
                   /* if(leaguesModel.sports != null){
                        data.setValue(leaguesModel.sports);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<LeaguesModel> call, Throwable t) {
                data.setValue(null);

            }
        });
        return data;
    }


}
