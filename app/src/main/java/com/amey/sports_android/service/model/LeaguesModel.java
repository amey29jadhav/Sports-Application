package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaguesModel {

    public List<Leagues> leagues = null;

    public LeaguesModel(){}

    public LeaguesModel(List<Leagues> leagues){
        this.leagues = leagues;
    }




    public class Leagues1{
        @SerializedName("idLeague")
        @Expose
        public String idLeague;

        @SerializedName("strLeague")
        @Expose
        public String strLeague;

        @SerializedName("strSport")
        @Expose
        public String strSport;

        @SerializedName("strLeagueAlternate")
        @Expose
        public String strLeagueAlternate;

        public Leagues1(){}

        public Leagues1(String idLeague, String strLeague, String strSport, String strLeagueAlternate){
            this.idLeague = idLeague;
            this.strLeague = strLeague;
            this.strSport = strSport;
            this.strLeagueAlternate = strLeagueAlternate;

        }

    }


}
