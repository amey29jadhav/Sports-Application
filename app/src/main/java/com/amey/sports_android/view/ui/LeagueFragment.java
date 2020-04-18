package com.amey.sports_android.view.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.view.adapter.LeagueAdapter;
import com.amey.sports_android.view.adapter.SportsAdapter;
import com.amey.sports_android.viewmodel.LeagueViewModel;
import com.amey.sports_android.viewmodel.SportsViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeagueFragment extends Fragment {

    public static String tag ="LeagueFragment";
    private LeagueViewModel mViewModel;
    RecyclerView leagueRecyclerView;
    private View view;
    Context context;
    LeagueAdapter leagueAdapter;
    private String sportname;
    List<Leagues> sortedList = new ArrayList<>();


    public static LeagueFragment newInstance() {
        return new LeagueFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        sportname = getArguments().getString("sportname");

        view = inflater.inflate(R.layout.league_fragment, container, false);
        leagueRecyclerView = (RecyclerView) view.findViewById(R.id.leagueRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        leagueRecyclerView.setLayoutManager(linearLayoutManager);
        leagueAdapter = new LeagueAdapter();
        leagueRecyclerView.setAdapter(leagueAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LeagueViewModel.class);
        observeViewModel(mViewModel);
        // TODO: Use the ViewModel
    }

    private void observeViewModel(LeagueViewModel mViewModel) {
        mViewModel.getLeagueListObservable(SportsApi.newInstance()).observe(getViewLifecycleOwner(), new Observer<List<Leagues>>() {
            @Override
            public void onChanged(List<Leagues> leagues) {
                if (leagues != null) {
                    if(sortedList.size() > 0)
                        sortedList.clear();

                    sortedList.addAll(leagues);
                    //binding.setIsLoading(false);
                    Iterator<Leagues> iterator = sortedList.iterator();
                    while(iterator.hasNext()){
                        Leagues currentLeague = (Leagues)iterator.next();
                        if(!currentLeague.strSport.equalsIgnoreCase(sportname)){
                            iterator.remove();

                        }
                    }
                    leagueAdapter.setLeaguesList(sortedList);
                }
            }
        });

    }


}
