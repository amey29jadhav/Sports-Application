package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingModel {

    @SerializedName("table")
    public List<Standing> table;

    public StandingModel(){}

    public class Standing{

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("teamid")
        @Expose
        public String teamid;

        @SerializedName("played")
        @Expose
        public int played;

        @SerializedName("goalsfor")
        @Expose
        public int goalsfor;

        @SerializedName("goalsagainst")
        @Expose
        public int goalsagainst;

        @SerializedName("goalsdifference")
        @Expose
        public int goalsdifference;

        @SerializedName("win")
        @Expose
        public int win;

        @SerializedName("draw")
        @Expose
        public int draw;

        @SerializedName("loss")
        @Expose
        public int loss;

        @SerializedName("total")
        @Expose
        public int total;

        public Standing(){}
    }
}
