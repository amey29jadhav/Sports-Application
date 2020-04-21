package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamModel {

    @SerializedName("teams")
    @Expose
    public List<Team> teams;

    public TeamModel(){

    }
    public TeamModel(List<Team> teams){
        this.teams = teams;
    }

    public class Team{

        @SerializedName("strTeam")
        @Expose
        public String strTeam ;

        @SerializedName("strTeamLogo")
        @Expose
        public String strTeamLogo;

        @SerializedName("idTeam")
        @Expose
        public String idTeam;


        public Team(){}

        public Team(String strTeam){
            this.strTeam = strTeam;

        }

    }
}
