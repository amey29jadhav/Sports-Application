package com.amey.sports_android.view.ui;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.service.repository.GameService;
import com.amey.sports_android.service.repository.RetrofitClientInstance;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.view.adapter.SportsAdapter;
import com.amey.sports_android.view.adapter.TeamAdapter;
import com.amey.sports_android.view.callback.ClickCallback;
import com.amey.sports_android.viewmodel.TeamViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamFragment extends Fragment {

    private TeamViewModel mViewModel;
    public final static String tag = "TeamFragment";
    TeamAdapter teamAdapter;
    Context context;
    RecyclerView recyclerView;
    SearchView searchview;
    View view;
    private String leagueName;
    ClickCallback<String> clickCallback;


    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        leagueName = getArguments().getString("leagueName");


        view = inflater.inflate(R.layout.team_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.sportsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        teamAdapter = new TeamAdapter(this.context,clickCallback);
        recyclerView.setAdapter(teamAdapter);

        searchview = view.findViewById(R.id.searchview);
        searchview.setIconified(false);
        searchview.clearFocus();
        searchview.setQueryHint("Search Team");
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                teamAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                teamAdapter.getFilter().filter(newText);

                return true;
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);

        observeViewModel(mViewModel);
        // TODO: Use the ViewModel
    }

    private void observeViewModel(TeamViewModel mViewModel) {
        mViewModel.getTeamListObservable(SportsApi.newInstance(),leagueName).observe(getViewLifecycleOwner(), new Observer<List<TeamModel.Team>>() {
            @Override
            public void onChanged(List<TeamModel.Team> teams) {
                if (teams != null) {
                    teamAdapter.setTeamList(teams);
                    //sportsAdapter.setSportList(sports);

                }

            }
        });
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;

    }




}
