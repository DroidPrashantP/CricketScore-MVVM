package app.cricket.prashant.com.cricketapp.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import app.cricket.prashant.com.cricketapp.model.GetData;

/**
 * Created by prashant on 2/11/17.
 */

public class Matches implements Serializable{

    @SerializedName("matches") private List<GetData> mMatchesList;

    public List<GetData> getMatchesList() {
        return mMatchesList;
    }

}
