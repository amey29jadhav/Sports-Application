package com.amey.sports_android.view.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.LeaguesModel;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.SportsModel;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.service.repository.GameService;
import com.amey.sports_android.service.repository.RetrofitClientInstance;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.utilities.AppConstant;
import com.amey.sports_android.utilities.AppSettings;
import com.amey.sports_android.utilities.NetworkUtils;
import com.amey.sports_android.utilities.Prefs;
import com.amey.sports_android.utilities.TypeFaceHelper;
import com.amey.sports_android.view.callback.ResultInterface;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashFragment extends Fragment {


    public String tag ="SplashFragment";
    MainActivity mainActivity;
    AppCompatTextView splashtitle;
    View view;
    Typeface robotoRegular;
    private Context context;


    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)context;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        robotoRegular = TypeFaceHelper.getInstance(mainActivity).getStyleTypeFace(TypeFaceHelper.MEDIUM);
        view = inflater.inflate(R.layout.fragment_splash,container,false);
        /*splashtitle = view.findViewById(R.id.splashtitle);
        splashtitle.setTypeface(robotoRegular);*/
        sportsLeaguesParallelCalls();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (NetworkUtils.isNetworkConnected(mainActivity)) {
            if (Prefs.getTeamId(getActivity()).isEmpty()) {
                LiveData<List<Sports>> sportsData = SportsApi.newInstance().getAllSports(new ResultInterface<Sports>() {
                    @Override
                    public void onSuccess(List<Sports> sports) {
                        if (sports != null) {
                            MainActivity.lstsports = sports;
                        }
                        //AppSettings.scheduleJob(context);

                        SportsApi.newInstance().getAllLeague(new ResultInterface<Leagues>() {
                            @Override
                            public void onSuccess(List<Leagues> leagues) {
                                if (leagues != null) {
                                    MainActivity.lstleagues = leagues;
                                }
                                if (Prefs.getTeamId(context).isEmpty()) {
                                    mainActivity.openSportFragment();
                                } else {
                                    mainActivity.openHomeFragment(Prefs.getTeamId(getActivity()));

                                }

                            }

                            @Override
                            public void onError() {

                            }
                        }).getValue();
                    }

                    @Override
                    public void onError() {

                    }
                });
            } else {
                mainActivity.openHomeFragment(Prefs.getTeamId(getActivity()));

            }
        }else{
            Toast.makeText(mainActivity,"Please Check your Internet Connection.",Toast.LENGTH_LONG).show();
        }
    }


    public void sportsLeaguesParallelCalls(){
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);


        compositeDisposable.add(gameService.getAllSportsObservable(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.GET_ALL_SPORTS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SportsModel>() {
                    @Override
                    public void accept(SportsModel sportsModel) throws Exception {
                        if(sportsModel != null){
                            System.out.println();
                        }

                    }
                }));

        compositeDisposable.add(gameService.getAllLeaguesObservable(AppConstant.API_HOST_NAME+AppConstant.PATH_NAME+AppConstant.API_KEY+AppConstant.GET_ALL_LEAGUES).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LeaguesModel>() {
            @Override
            public void accept(LeaguesModel leaguesModel) throws Exception {
                if(leaguesModel != null){
                    System.out.println();
                }
            }
        }));


    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
