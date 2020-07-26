package com.amey.sports_android.view.ui;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.Rect;
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
import com.amey.sports_android.utilities.AppConstant;
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
    AppCompatTextView nogamesTextview, nolastgamesTextview;
    MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.mainActivity = (MainActivity)context;
    }



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        teamId = getArguments().getString("teamId");
        mainActivity.headertextview.setText(AppConstant.EVENTS);


        view = inflater.inflate(R.layout.home_fragment, container, false);
        nogamesTextview = view.findViewById(R.id.nogamesTextview);
        nogamesTextview.setVisibility(View.GONE);

        nolastgamesTextview = view.findViewById(R.id.nolastgamesTextview);
        nolastgamesTextview.setVisibility(View.GONE);
        upcomingGameRecyclerView = (RecyclerView) view.findViewById(R.id.upcomingGameRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        upcomingGameRecyclerView.setLayoutManager(linearLayoutManager);
        upcomingGameRecyclerView.addItemDecoration(new CustomItemDecoration(5));

        upComingEventsAdapter = new UpComingEventsAdapter(this.context,clickCallback);
        upcomingGameRecyclerView.setAdapter(upComingEventsAdapter);

        pastGameRecyclerView = (RecyclerView) view.findViewById(R.id.pastGameRecyclerView);
        //pastGameRecyclerView.setItemAnimator(new DefaultItemAnimator());
        pastGameRecyclerView.addItemDecoration(new SpacesItemDecoration(5,getResources().getColor(R.color.separator_color),0.5f,context));
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
                if (events != null && events.size()> 0) {
                    upcomingGameRecyclerView.setVisibility(View.VISIBLE);
                    nogamesTextview.setVisibility(View.GONE);
                    upComingEventsAdapter.setEventsList(events);
                    //sportsAdapter.setSportList(sports);

                }else{
                    upcomingGameRecyclerView.setVisibility(View.GONE);
                    nogamesTextview.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    private void observeLastEventViewModel(PastEventViewModel mViewModel) {
        mViewModel.getLastEventsListObservable(SportsApi.newInstance(),teamId).observe(getViewLifecycleOwner(), new Observer<List<LastEventModel.Events>>() {
            @Override
            public void onChanged(List<LastEventModel.Events> events) {
                if (events != null && events.size()> 0) {
                    pastGameRecyclerView.setVisibility(View.VISIBLE);
                    nolastgamesTextview.setVisibility(View.GONE);
                    lastGameAdapter.setEventsList(events);
                    //sportsAdapter.setSportList(sports);

                }else{
                    nolastgamesTextview.setVisibility(View.VISIBLE);
                    pastGameRecyclerView.setVisibility(View.GONE);
                }

            }
        });
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;

    }

    public class CustomItemDecoration extends RecyclerView.ItemDecoration{
        int space;
        public CustomItemDecoration(int space){
            this.space = space;

        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;


        }
    }

}
