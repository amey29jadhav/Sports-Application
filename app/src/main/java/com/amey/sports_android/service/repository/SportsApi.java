package com.amey.sports_android.service.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.LastEventModel;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.LeaguesModel;
import com.amey.sports_android.service.model.SeasonModel;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.SportsModel;
import com.amey.sports_android.service.model.Standing;
import com.amey.sports_android.service.model.StandingModel;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.utilities.AppConstant;
import com.amey.sports_android.view.callback.ResultInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsApi {

    private SportsApi(){

    }

    private static SportsApi sportsApi;

    public static SportsApi newInstance(){
        if(sportsApi == null){
            sportsApi = new SportsApi();
        }
        return sportsApi;
    }

    public LiveData<List<Sports>> getAllSports(final ResultInterface<Sports> resultInterface){
        final MutableLiveData<List<Sports>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<SportsModel> listSport = gameService.getAllSports(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.GET_ALL_SPORTS);
        listSport.enqueue(new Callback<SportsModel>() {
            @Override
            public void onResponse(Call<SportsModel> call, Response<SportsModel> response) {
                if(response != null){
                    SportsModel sportsModel =response.body();
                    if(sportsModel.sports != null && resultInterface != null){
                        data.setValue(sportsModel.sports);
                        resultInterface.onSuccess(sportsModel.sports);
                    }

                }
            }

            @Override
            public void onFailure(Call<SportsModel> call, Throwable t) {
                    data.setValue(null);

                if(resultInterface != null){
                    resultInterface.onError();
                }


            }
        });
        return data;
    }



    public LiveData<List<Leagues>> getAllLeague(final ResultInterface<Leagues> resultInterface){
        final MutableLiveData<List<Leagues>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<LeaguesModel> listSport = gameService.getAllLeagues(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.GET_ALL_LEAGUES);
        listSport.enqueue(new Callback<LeaguesModel>() {
            @Override
            public void onResponse(Call<LeaguesModel> call, Response<LeaguesModel> response) {
                if(response != null){
                    LeaguesModel leaguesModel =response.body();
                    if(leaguesModel.leagues != null && resultInterface != null){
                        data.setValue(leaguesModel.leagues);
                        resultInterface.onSuccess(leaguesModel.leagues);

                    }
                   /* if(leaguesModel.sports != null){
                        data.setValue(leaguesModel.sports);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<LeaguesModel> call, Throwable t) {
                data.setValue(null);
                if(resultInterface != null){
                    resultInterface.onError();
                }
            }
        });
        return data;
    }


    public LiveData<List<TeamModel.Team>> getAllTeams(final ResultInterface<TeamModel.Team> resultInterface, String leagueName){
        final MutableLiveData<List<TeamModel.Team>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<TeamModel> listSport = gameService.getAllTeams(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.SEARCH_ALL_TEAMS,leagueName);
        listSport.enqueue(new Callback<TeamModel>() {
            @Override
            public void onResponse(Call<TeamModel> call, Response<TeamModel> response) {
                if(response != null){
                    TeamModel teamModel =response.body();
                    if(teamModel.teams != null){
                        data.setValue(teamModel.teams);
                        //resultInterface.onSuccess(teamModel.teams);

                    }
                   /* if(leaguesModel.sports != null){
                        data.setValue(leaguesModel.sports);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<TeamModel> call, Throwable t) {
                data.setValue(null);
                if(resultInterface != null){
                    resultInterface.onError();
                }
            }
        });
        return data;
    }

    public LiveData<List<EventsModel.Events>> getUpcomingEvents(final ResultInterface<EventsModel.Events> resultInterface, String teamId){
        final MutableLiveData<List<EventsModel.Events>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<EventsModel> listSport = gameService.getAllUpComingEvents(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.EVENTS_NEXT,teamId);
        listSport.enqueue(new Callback<EventsModel>() {
            @Override
            public void onResponse(Call<EventsModel> call, Response<EventsModel> response) {
                if(response != null){
                    EventsModel eventsModel =response.body();
                    data.setValue(eventsModel.events);


                   /* if(leaguesModel.sports != null){
                        data.setValue(leaguesModel.sports);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<EventsModel> call, Throwable t) {
                data.setValue(null);
                if(resultInterface != null){
                    resultInterface.onError();
                }
            }
        });
        return data;
    }

    public LiveData<List<LastEventModel.Events>> getLastEvents(final ResultInterface<LastEventModel.Events> resultInterface, String teamId){
        final MutableLiveData<List<LastEventModel.Events>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<LastEventModel> listSport = gameService.getAllLastEvents(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.EVENTS_LAST,teamId);
        listSport.enqueue(new Callback<LastEventModel>() {
            @Override
            public void onResponse(Call<LastEventModel> call, Response<LastEventModel> response) {
                if(response != null){
                    LastEventModel eventsModel =response.body();
                    data.setValue(eventsModel.results);

                   /* if(leaguesModel.sports != null){
                        data.setValue(leaguesModel.sports);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<LastEventModel> call, Throwable t) {
                data.setValue(null);
                if(resultInterface != null){
                    resultInterface.onError();
                }
            }
        });
        return data;
    }

    public LiveData<List<SeasonModel.Season>> getAllSeasons(final ResultInterface<SeasonModel.Season> resultInterface, String leagueId){
        final MutableLiveData<List<SeasonModel.Season>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<SeasonModel> listSport = gameService.getAllSeasons(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.SEARCH_ALL_SESSION,leagueId);
        listSport.enqueue(new Callback<SeasonModel>() {
            @Override
            public void onResponse(Call<SeasonModel> call, Response<SeasonModel> response) {
                if(response != null){
                    SeasonModel seasonModel =response.body();
                    if(seasonModel.seasons != null){
                        data.setValue(seasonModel.seasons);
                        //resultInterface.onSuccess(teamModel.teams);

                    }
                   /* if(leaguesModel.sports != null){
                        data.setValue(leaguesModel.sports);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<SeasonModel> call, Throwable t) {
                data.setValue(null);
                if(resultInterface != null){
                    resultInterface.onError();
                }
            }
        });
        return data;
    }

    public LiveData<List<Standing>> getAllStanding(final ResultInterface<Standing> resultInterface, String leagueId, String seasonId){
        final MutableLiveData<List<Standing>> data = new MutableLiveData<>();

        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<StandingModel> listSport = gameService.getAllStanding(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.LOOKUP_ALL_STANDING,leagueId, seasonId);
        listSport.enqueue(new Callback<StandingModel>() {
            @Override
            public void onResponse(Call<StandingModel> call, Response<StandingModel> response) {
                if(response != null){
                    StandingModel standingModel =response.body();
                    if(standingModel.table != null){
                        data.setValue(standingModel.table);
                        //resultInterface.onSuccess(teamModel.teams);

                    }
                   /* if(leaguesModel.sports != null){
                        data.setValue(leaguesModel.sports);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<StandingModel> call, Throwable t) {
                data.setValue(null);
                if(resultInterface != null){
                    resultInterface.onError();
                }
            }
        });
        return data;
    }




}
