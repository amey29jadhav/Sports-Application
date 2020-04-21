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
import android.widget.ExpandableListView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.StandingModel;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.utilities.Prefs;
import com.amey.sports_android.utilities.SpacesItemDecoration;
import com.amey.sports_android.view.adapter.SportsAdapter;
import com.amey.sports_android.view.adapter.StandingAdapter;
import com.amey.sports_android.viewmodel.SportsViewModel;
import com.amey.sports_android.viewmodel.StandingViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class StandingFragment extends Fragment {

    public static String tag ="StandingFragment";
    private StandingViewModel mViewModel;
    public Context context;
    public View view;
    RecyclerView standingsRecyclerView;
    String seasonId;
    StandingAdapter standingAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static StandingFragment newInstance() {
        return new StandingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        seasonId = getArguments().getString("seasonId");

        view = inflater.inflate(R.layout.standing_fragment, container, false);
        standingsRecyclerView = (RecyclerView) view.findViewById(R.id.standingsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        standingsRecyclerView.setLayoutManager(linearLayoutManager);
        standingsRecyclerView.addItemDecoration(new SpacesItemDecoration(5,getResources().getColor(R.color.colorAccent),0.5f,context));
        standingAdapter = new StandingAdapter(this.context);
        standingsRecyclerView.setAdapter(standingAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StandingViewModel.class);
        observeViewModel(mViewModel);
        // TODO: Use the ViewModel
    }
    private void observeViewModel(StandingViewModel mViewModel) {
        mViewModel.getStandingsListObservable(SportsApi.newInstance(), Prefs.getLeagueId(context),seasonId).observe(getViewLifecycleOwner(), new Observer<List<StandingModel.Standing>>() {
            @Override
            public void onChanged(List<StandingModel.Standing> standings) {

                if (standings != null) {
                    standingAdapter.setStandingList(standings);

                }
            }
        });
    }
}
