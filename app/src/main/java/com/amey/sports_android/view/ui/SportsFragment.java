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
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.repository.SportsApi;
import com.amey.sports_android.view.adapter.SportsAdapter;
import com.amey.sports_android.viewmodel.SportsViewModel;

import java.util.Collections;
import java.util.List;


public class SportsFragment extends Fragment {
    public static String tag ="SportsFragment";
    SportsAdapter sportsAdapter;


    private SportsViewModel mViewModel;
    private View view;
    Context context;
    RecyclerView recyclerView;
    ClickCallback clickCallback;


    public static SportsFragment newInstance() {
        return new SportsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.sports_fragment, container, false);
         recyclerView = (RecyclerView) view.findViewById(R.id.sportsRecyclerView);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
         recyclerView.setLayoutManager(linearLayoutManager);
         sportsAdapter = new SportsAdapter(this.clickCallback);
         recyclerView.setAdapter(sportsAdapter);
         return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SportsViewModel.class);
        observeViewModel(mViewModel);

        // TODO: Use the ViewModel
    }

    private void observeViewModel(SportsViewModel mViewModel) {
        mViewModel.getSportsListObservable(SportsApi.newInstance()).observe(getViewLifecycleOwner(), new Observer<List<Sports>>() {
            @Override
            public void onChanged(List<Sports> sports) {
                if (sports != null) {
                    //binding.setIsLoading(false);
                    //sports.sort(String::compareToIgnoreCase);

                    MainActivity.lstsports = sports;
                    sportsAdapter.setSportList(sports);
                }
            }
        });

    }
    public interface ClickCallback{
        void onClick(String name);
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;

    }

}
