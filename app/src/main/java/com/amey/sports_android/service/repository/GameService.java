package com.amey.sports_android.service.repository;

import com.amey.sports_android.service.model.LeaguesModel;
import com.amey.sports_android.service.model.SportsModel;
import com.amey.sports_android.service.model.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GameService {

    @GET
    Call<LeaguesModel> getAllLeagues(@Url String url);

    @GET
    Call<SportsModel> getAllSports(@Url String url);
    @GET
    Call<List<TeamModel>> listTeam(@Url String url);


}
