package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeasonModel {


    @SerializedName("seasons")
    @Expose
    public List<Season> seasons;

    public SeasonModel(){}

    public class Season{

        @SerializedName("strSeason")
        @Expose
        public String strSeason;

    }
}
