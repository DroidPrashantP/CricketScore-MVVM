package app.cricket.prashant.com.cricketapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by prashant on 2/11/17.
 */

public class GetScore implements Serializable {

    @SerializedName("matchStarted")
    public boolean matchStarted;
    @SerializedName("team-2")
    public String teamSecond;
    @SerializedName("team-1")
    public String teamOne;
    @SerializedName("dateTimeGMT")
    public String dateTimeGMT;
    @SerializedName("type")
    public String type;
    @SerializedName("score")
    public String score;
    @SerializedName("innings-requirement")
    public String inningsRequirement;
}
