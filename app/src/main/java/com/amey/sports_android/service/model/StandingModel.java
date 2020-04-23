package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingModel {

    @SerializedName("table")
    public List<Standing> table;

    public StandingModel(){}


}
