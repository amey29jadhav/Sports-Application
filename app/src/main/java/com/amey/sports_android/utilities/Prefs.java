package com.amey.sports_android.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.amey.sports_android.view.ui.MainActivity;

public class Prefs {

    public static String myPreference = "sports";
    static SharedPreferences preferences;

    public static void saveTeamId(Context context, String teamId){
        preferences = context.getSharedPreferences(myPreference,Context.MODE_PRIVATE);
       preferences.edit().putString("teamId",teamId).apply();

    }

    public static String  getTeamId(Context context){
         preferences = context.getSharedPreferences(myPreference,Context.MODE_PRIVATE);
           return preferences.getString("teamId","");
    }

    public static void clearPrefs(Context context){
        preferences = context.getSharedPreferences(myPreference,Context.MODE_PRIVATE);
         preferences.edit().clear().apply();
    }


    public static String getLeagueId(Context context) {
        preferences = context.getSharedPreferences(myPreference,Context.MODE_PRIVATE);
        return preferences.getString("leagueId","");
    }
    public static void saveLeagueId(Context context, String teamId){
        preferences = context.getSharedPreferences(myPreference,Context.MODE_PRIVATE);
        preferences.edit().putString("leagueId",teamId).apply();

    }
}
