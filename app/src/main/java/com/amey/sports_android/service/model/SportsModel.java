package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.PublicKey;
import java.util.List;

public class SportsModel {

    @SerializedName("sports")
    @Expose
    public List<Sports> sports = null;

    public SportsModel(){}

    public SportsModel(List<Sports> sports){
        super();
        this.sports = sports;
    }


}
