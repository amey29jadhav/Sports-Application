package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leagues {

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

    public Leagues(){}

    public Leagues(String idLeague, String strLeague, String strSport, String strLeagueAlternate){
        this.idLeague = idLeague;
        this.strLeague = strLeague;
        this.strSport = strSport;
        this.strLeagueAlternate = strLeagueAlternate;

    }

}
