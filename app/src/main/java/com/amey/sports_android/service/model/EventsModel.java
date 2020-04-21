package com.amey.sports_android.service.model;

import com.amey.sports_android.view.adapter.LastGameAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsModel  {

    @SerializedName("events")
    @Expose
    public List<Events> events;


    public EventsModel(){

    }


    public EventsModel(List<Events> lstEvents){
        this.events =lstEvents;
    }

    public class Events{

        @SerializedName("idEvent")
        @Expose
        public String idEvent;
        @SerializedName("idSoccerXML")
        @Expose
        public String idSoccerXML;
        @SerializedName("idAPIfootball")
        @Expose
        public Object idAPIfootball;
        @SerializedName("strEvent")
        @Expose
        public String strEvent;
        @SerializedName("strEventAlternate")
        @Expose
        public String strEventAlternate;
        @SerializedName("strFilename")
        @Expose
        public String strFilename;
        @SerializedName("strSport")
        @Expose
        public String strSport;
        @SerializedName("idLeague")
        @Expose
        public String idLeague;
        @SerializedName("strLeague")
        @Expose
        public String strLeague;
        @SerializedName("strSeason")
        @Expose
        public String strSeason;
        @SerializedName("strDescriptionEN")
        @Expose
        public Object strDescriptionEN;
        @SerializedName("strHomeTeam")
        @Expose
        public String strHomeTeam;
        @SerializedName("strAwayTeam")
        @Expose
        public String strAwayTeam;
        @SerializedName("intHomeScore")
        @Expose
        public String intHomeScore;
        @SerializedName("intRound")
        @Expose
        public String intRound;
        @SerializedName("intAwayScore")
        @Expose
        public String intAwayScore;
        @SerializedName("intSpectators")
        @Expose
        public String intSpectators;
        @SerializedName("strHomeGoalDetails")
        @Expose
        public String strHomeGoalDetails;
        @SerializedName("strHomeRedCards")
        @Expose
        public String strHomeRedCards;
        @SerializedName("strHomeYellowCards")
        @Expose
        public String strHomeYellowCards;
        @SerializedName("strHomeLineupGoalkeeper")
        @Expose
        public String strHomeLineupGoalkeeper;
        @SerializedName("strHomeLineupDefense")
        @Expose
        public String strHomeLineupDefense;
        @SerializedName("strHomeLineupMidfield")
        @Expose
        public String strHomeLineupMidfield;
        @SerializedName("strHomeLineupForward")
        @Expose
        public String strHomeLineupForward;
        @SerializedName("strHomeLineupSubstitutes")
        @Expose
        public String strHomeLineupSubstitutes;
        @SerializedName("strHomeFormation")
        @Expose
        public String strHomeFormation;
        @SerializedName("strAwayRedCards")
        @Expose
        public String strAwayRedCards;
        @SerializedName("strAwayYellowCards")
        @Expose
        public String strAwayYellowCards;
        @SerializedName("strAwayGoalDetails")
        @Expose
        public String strAwayGoalDetails;
        @SerializedName("strAwayLineupGoalkeeper")
        @Expose
        public String strAwayLineupGoalkeeper;
        @SerializedName("strAwayLineupDefense")
        @Expose
        public String strAwayLineupDefense;
        @SerializedName("strAwayLineupMidfield")
        @Expose
        public String strAwayLineupMidfield;
        @SerializedName("strAwayLineupForward")
        @Expose
        public String strAwayLineupForward;
        @SerializedName("strAwayLineupSubstitutes")
        @Expose
        public String strAwayLineupSubstitutes;
        @SerializedName("strAwayFormation")
        @Expose
        public String strAwayFormation;
        @SerializedName("intHomeShots")
        @Expose
        public String intHomeShots;
        @SerializedName("intAwayShots")
        @Expose
        public String intAwayShots;
        @SerializedName("dateEvent")
        @Expose
        public String dateEvent;
        @SerializedName("dateEventLocal")
        @Expose
        public Object dateEventLocal;
        @SerializedName("strDate")
        @Expose
        public String strDate;
        @SerializedName("strTime")
        @Expose
        public String strTime;
        @SerializedName("strTimeLocal")
        @Expose
        public Object strTimeLocal;
        @SerializedName("strTVStation")
        @Expose
        public Object strTVStation;
        @SerializedName("idHomeTeam")
        @Expose
        public String idHomeTeam;
        @SerializedName("idAwayTeam")
        @Expose
        public String idAwayTeam;
        @SerializedName("strResult")
        @Expose
        public Object strResult;
        @SerializedName("strCircuit")
        @Expose
        public Object strCircuit;
        @SerializedName("strCountry")
        @Expose
        public Object strCountry;
        @SerializedName("strCity")
        @Expose
        public Object strCity;
        @SerializedName("strPoster")
        @Expose
        public Object strPoster;
        @SerializedName("strFanart")
        @Expose
        public Object strFanart;
        @SerializedName("strThumb")
        @Expose
        public Object strThumb;
        @SerializedName("strBanner")
        @Expose
        public Object strBanner;
        @SerializedName("strMap")
        @Expose
        public Object strMap;
        @SerializedName("strTweet1")
        @Expose
        public Object strTweet1;
        @SerializedName("strTweet2")
        @Expose
        public Object strTweet2;
        @SerializedName("strTweet3")
        @Expose
        public Object strTweet3;
        @SerializedName("strVideo")
        @Expose
        public Object strVideo;
        @SerializedName("strPostponed")
        @Expose
        public String strPostponed;
        @SerializedName("strLocked")
        @Expose
        public String strLocked;

        public Events(){}

        public Events(String strEvent, String strLeague, String strSport, String dateEvent){
            this.strEvent = strEvent;
            this.strLeague = strLeague;
            this.strSport = strSport;
            this.dateEvent = dateEvent;
        }
    }
}
