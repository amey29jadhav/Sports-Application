package com.amey.sports_android.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.os.Bundle;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.view.adapter.SportsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    TeamFragment teamFragment;
    LeagueFragment leagueFragment;
    SportsFragment sportsFragment;
    public static List<Sports> lstsports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sportsFragment = SportsFragment.newInstance();
        leagueFragment = LeagueFragment.newInstance();
        sportsFragment.setClickCallback(clickCallback);





        if(savedInstanceState ==null){
               FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
               fragmentTransaction.add(R.id.fragment_container ,sportsFragment,SportsFragment.tag).commit();
        }



    }

    void openLeagueFragment(String sportname){
        Bundle bundle = new Bundle();
        bundle.putString("sportname", sportname);
        leagueFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container ,leagueFragment,LeagueFragment.tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    SportsFragment.ClickCallback clickCallback =
    clickCallback = new SportsFragment.ClickCallback() {
        @Override
        public void onClick(String name) {

           openLeagueFragment(name);

        }
    };

}
