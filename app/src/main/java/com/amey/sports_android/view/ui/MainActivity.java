package com.amey.sports_android.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.os.Bundle;

import com.amey.sports_android.R;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    TeamFragment teamFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        if(savedInstanceState ==null){
               FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
               fragmentTransaction.add(R.id.fragment_container ,TeamFragment.newInstance(),TeamFragment.tag).commit();
        }

    }
}
