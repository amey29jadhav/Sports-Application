package com.amey.sports_android.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.StandingModel;
import com.amey.sports_android.utilities.AppConstant;
import com.amey.sports_android.utilities.Prefs;
import com.amey.sports_android.view.adapter.SportsAdapter;
import com.amey.sports_android.view.callback.ClickCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Leagues> lstleagues;
    FragmentTransaction fragmentTransaction;
    TeamFragment teamFragment;
    LeagueFragment leagueFragment;
    SportsFragment sportsFragment;
    SplashFragment splashFragment;
    HomeFragment homeFragment;
    SeasonFragment seasonFragment;
    StandingFragment standingFragment;
    BottomNavigationView bottom_navigation;
    AppCompatTextView settingstextview, headertextview;
    private Typeface fontAwesomeFont;

    Toolbar toolbar;
    AlertDialog alertDialog;



    public static List<Sports> lstsports = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontAwesomeFont = Typeface.createFromAsset(getAssets(), "FontAwesome.otf");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setVisibility(View.GONE);

        headertextview = findViewById(R.id.headertextview);

        settingstextview = findViewById(R.id.settingstextview);
        //settingstextview.setTypeface(fontAwesomeFont);

        alertDialog = new AlertDialog.Builder(this,R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
                .setTitle("Reset Preferences")
                .setMessage("Do you wish to continue")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Prefs.clearPrefs(MainActivity.this);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }). setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextSize(15);
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextSize(15);
            }
        });



        settingstextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
                //openSettingsFragment();
            }
        });
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setVisibility(View.GONE);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        openHomeFragment(Prefs.getTeamId(MainActivity.this));
                        return true;

                    case R.id.navigation_sms:
                        openSeasonFragment(Prefs.getLeagueId(MainActivity.this));
                        return true;

                    case R.id.navigation_notifications:
                        openHomeFragment(Prefs.getTeamId(MainActivity.this));
                        return true;


                }
                return false;
            }
        });

        splashFragment = SplashFragment.newInstance();
        sportsFragment = SportsFragment.newInstance();
        sportsFragment.setClickCallback(clickCallback);
        leagueFragment = LeagueFragment.newInstance();
        leagueFragment.setClickCallback(leagueClickCallback);

        homeFragment = HomeFragment.newInstance();
        homeFragment.setClickCallback(null);

        seasonFragment = SeasonFragment.newInstance();
        seasonFragment.setClickCallback(seasonClickCallback);

        standingFragment = StandingFragment.newInstance();


        teamFragment = TeamFragment.newInstance();
        teamFragment.setClickCallback(teamClickCallback);


        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, splashFragment, splashFragment.tag).commit();
        }


    }

    public void openSportFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, sportsFragment, SportsFragment.tag);
        fragmentTransaction.commit();
    }

    void openLeagueFragment(String sportname) {
        Bundle bundle = new Bundle();
        bundle.putString("sportname", sportname);
        leagueFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, leagueFragment, LeagueFragment.tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void openTeamFragment(String leagueName) {
        Bundle bundle = new Bundle();
        bundle.putString("leagueName", leagueName);
        teamFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, teamFragment, TeamFragment.tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void openHomeFragment(String teamId){
        bottom_navigation.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
        headertextview.setText(AppConstant.EVENTS);
        Bundle bundle = new Bundle();
        bundle.putString("teamId", teamId);
        homeFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment, HomeFragment.tag);
        fragmentTransaction.commit();
    }

    void openSeasonFragment(String leaueId){
        bottom_navigation.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
        headertextview.setText(AppConstant.SEASONS);
        Bundle bundle = new Bundle();
        bundle.putString("leagueId", leaueId);
        seasonFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, seasonFragment, SeasonFragment.tag);
        fragmentTransaction.commit();
    }

    void openStandingFragment(String seasonId){
        bottom_navigation.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
        headertextview.setText(AppConstant.SEASONS);
        Bundle bundle = new Bundle();
        bundle.putString("seasonId", seasonId);
        standingFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, standingFragment, StandingFragment.tag);
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

    ClickCallback<String> leagueClickCallback = new ClickCallback<String>() {
        @Override
        public void onClick(String name) {
            openTeamFragment(name);
        }
    };

    ClickCallback<String> teamClickCallback = new ClickCallback<String>() {
        @Override
        public void onClick(String teamID) {
            openHomeFragment(teamID);
        }
    };

    ClickCallback<String> seasonClickCallback =new ClickCallback<String>() {
        @Override
        public void onClick(String name) {
            openStandingFragment(name);
        }
    };

}
