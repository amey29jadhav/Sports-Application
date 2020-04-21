package com.amey.sports_android.service.repository;

import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.LastEventModel;
import com.amey.sports_android.service.model.LeaguesModel;
import com.amey.sports_android.service.model.SeasonModel;
import com.amey.sports_android.service.model.SportsModel;
import com.amey.sports_android.service.model.StandingModel;
import com.amey.sports_android.service.model.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GameService {

    @GET
    Call<LeaguesModel> getAllLeagues(@Url String url);

    @GET
    Call<SportsModel> getAllSports(@Url String url);

    @GET
    Call<TeamModel> getAllTeams(@Url String url, @Query("l") String leaguename);

    @GET
    Call<EventsModel> getAllUpComingEvents(@Url String url, @Query("id") String teamId);

    @GET
    Call<LastEventModel> getAllLastEvents(@Url String url, @Query("id") String teamId);

    @GET
    Call<SeasonModel> getAllSeasons(@Url String url, @Query("id") String leagueId);

    @GET
    Call<StandingModel> getAllStanding(@Url String url, @Query("l") String leagueId, @Query("s") String seasonId);


}
