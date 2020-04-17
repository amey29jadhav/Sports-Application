package com.amey.sports_android.view.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.service.repository.GameService;
import com.amey.sports_android.service.repository.RetrofitClientInstance;
import com.amey.sports_android.viewmodel.TeamViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamFragment extends Fragment {

    private TeamViewModel mViewModel;
    public final static String tag ="TeamFragment";


    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.team_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        GameService gameService = RetrofitClientInstance.newInstance().create(GameService.class);
        Call<List<TeamModel>> lstTeamModel = gameService.listTeam("https://www.thesportsdb.com/api/v1/json/1/all_sports.php");
        lstTeamModel.enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {
                System.out.println();
            }

            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {
                System.out.println();
            }
        });

        //observeViewModel(mViewModel);
        // TODO: Use the ViewModel
    }

    private void observeViewModel(TeamViewModel mViewModel) {
        mViewModel.getTeamListObservable().observe(getViewLifecycleOwner(), new Observer<List<TeamModel>>() {
            @Override
            public void onChanged(List<TeamModel> teamModels) {
                if(teamModels != null){

                }

            }
        });
    }

}
