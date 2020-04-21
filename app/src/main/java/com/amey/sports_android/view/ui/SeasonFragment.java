package com.amey.sports_android.view.ui;

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
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.SeasonModel;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.utilities.SpacesItemDecoration;
import com.amey.sports_android.view.adapter.LeagueAdapter;
import com.amey.sports_android.view.adapter.SeasonAdapter;
import com.amey.sports_android.view.callback.ClickCallback;
import com.amey.sports_android.viewmodel.LeagueViewModel;
import com.amey.sports_android.viewmodel.SeasonViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeasonFragment extends Fragment {

    public RecyclerView seasonRecyclerView;
    public static String tag ="SeasonFragment";
    private View view;
    Context context;
    SeasonAdapter seasonAdapter;
    private String leagueId;
    ClickCallback clickCallback;


    private SeasonViewModel mViewModel;

    public static SeasonFragment newInstance() {
        return new SeasonFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        leagueId = getArguments().getString("leagueId");

        view = inflater.inflate(R.layout.season_fragment, container, false);
        seasonRecyclerView = (RecyclerView) view.findViewById(R.id.seasonRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        seasonRecyclerView.setLayoutManager(linearLayoutManager);
        seasonRecyclerView.addItemDecoration(new SpacesItemDecoration(5,getResources().getColor(R.color.colorAccent),0.5f,context));
        seasonAdapter = new SeasonAdapter(this.context,clickCallback);
        seasonRecyclerView.setAdapter(seasonAdapter);

        return view;
    }

    private void observeViewModel(SeasonViewModel mViewModel) {
        mViewModel.getSeasonListObservable(SportsApi.newInstance(),leagueId).observe(getViewLifecycleOwner(), new Observer<List<SeasonModel.Season>>() {
            @Override
            public void onChanged(List<SeasonModel.Season> seasons) {
                if (seasons != null) {
                    seasonAdapter.setSeasonList(seasons);
                }
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SeasonViewModel.class);
        observeViewModel(mViewModel);
        // TODO: Use the ViewModel
    }
    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;

    }
}
