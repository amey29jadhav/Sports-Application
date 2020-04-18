package com.amey.sports_android.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sports implements Comparable<Sports>{
    @SerializedName("idSport")
    @Expose
    public String idSport;


    @SerializedName("strSport")
    @Expose
    public String strSport;

    @SerializedName("strFormat")
    @Expose
    public String strFormat;


    @SerializedName("strSportThumb")
    @Expose
    public String strSportThumb;


    @SerializedName("strSportThumbGreen")
    @Expose
    public String strSportThumbGreen;


    @SerializedName("strSportDescription")
    @Expose
    public String strSportDescription;

    public Sports(){}

    public Sports(String idSport, String strSport, String strFormat, String strSportThumb, String strSportThumbGreen,String strSportDescription){
        this.idSport = idSport;
        this.strSport = strSport;
        this.strFormat = strFormat;
        this.strSportThumb = strSportThumb;
        this.strSportThumbGreen = strSportThumbGreen;
        this.strSportDescription = strSportDescription;
    }


    @Override
    public int compareTo(Sports sports) {
        return this.strSport.compareTo(sports.strSport);
    }
}
