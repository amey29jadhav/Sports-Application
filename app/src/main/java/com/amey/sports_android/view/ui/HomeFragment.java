package com.amey.sports_android.view.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.LastEventModel;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.utilities.SpacesItemDecoration;
import com.amey.sports_android.view.adapter.LastGameAdapter;
import com.amey.sports_android.view.adapter.TeamAdapter;
import com.amey.sports_android.view.adapter.UpComingEventsAdapter;
import com.amey.sports_android.view.callback.ClickCallback;
import com.amey.sports_android.viewmodel.PastEventViewModel;
import com.amey.sports_android.viewmodel.TeamViewModel;
import com.amey.sports_android.viewmodel.UpComingViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private UpComingViewModel mViewModel;
    private PastEventViewModel pastEventViewModel;
    RecyclerView upcomingGameRecyclerView, pastGameRecyclerView;
    UpComingEventsAdapter upComingEventsAdapter;
    public final static String tag = "HomeFragment";
    public View view;
    Context context;
    private String teamId;
    ClickCallback clickCallback;
    LastGameAdapter lastGameAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        teamId = getArguments().getString("teamId");

        view = inflater.inflate(R.layout.home_fragment, container, false);
        upcomingGameRecyclerView = (RecyclerView) view.findViewById(R.id.upcomingGameRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        upcomingGameRecyclerView.setLayoutManager(linearLayoutManager);
        upcomingGameRecyclerView.addItemDecoration(new SpacesItemDecoration(5,getResources().getColor(R.color.colorAccent),0.5f,context));

        upComingEventsAdapter = new UpComingEventsAdapter(this.context,clickCallback);
        upcomingGameRecyclerView.setAdapter(upComingEventsAdapter);

        pastGameRecyclerView = (RecyclerView) view.findViewById(R.id.pastGameRecyclerView);
        //pastGameRecyclerView.setItemAnimator(new DefaultItemAnimator());
        pastGameRecyclerView.addItemDecoration(new SpacesItemDecoration(5,getResources().getColor(R.color.colorAccent),0.5f,context));
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        pastGameRecyclerView.setLayoutManager(linearLayoutManager1);
        lastGameAdapter = new LastGameAdapter(this.context,clickCallback);
        pastGameRecyclerView.setAdapter(lastGameAdapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpComingViewModel.class);
        pastEventViewModel = ViewModelProviders.of(this).get(PastEventViewModel.class);

        // TODO: Use the ViewModel
        observeViewModel(mViewModel);
        observeLastEventViewModel(pastEventViewModel);

    }

    private void observeViewModel(UpComingViewModel mViewModel) {
        mViewModel.getEventsListObservable(SportsApi.newInstance(),teamId).observe(getViewLifecycleOwner(), new Observer<List<EventsModel.Events>>() {
            @Override
            public void onChanged(List<EventsModel.Events> events) {
                if (events != null) {
                    upComingEventsAdapter.setEventsList(events);
                    //sportsAdapter.setSportList(sports);

                }

            }
        });
    }
    private void observeLastEventViewModel(PastEventViewModel mViewModel) {
        mViewModel.getLastEventsListObservable(SportsApi.newInstance(),teamId).observe(getViewLifecycleOwner(), new Observer<List<LastEventModel.Events>>() {
            @Override
            public void onChanged(List<LastEventModel.Events> events) {
                if (events != null) {
                    lastGameAdapter.setEventsList(events);
                    //sportsAdapter.setSportList(sports);

                }

            }
        });
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;

    }

}
