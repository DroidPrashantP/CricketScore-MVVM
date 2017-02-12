package app.cricket.prashant.com.cricketapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by prashant on 2/11/17.
 */

public class GetData implements Serializable {
    @SerializedName("unique_id")
    public int unique_id;
    @SerializedName("date")
    public String date;
    @SerializedName("squad")
    public boolean squad;
    @SerializedName("team-2")
    public String team2;
    @SerializedName("team-1")
    public String team1;
    @SerializedName("matchStarted")
    public boolean matchStarted;
}
