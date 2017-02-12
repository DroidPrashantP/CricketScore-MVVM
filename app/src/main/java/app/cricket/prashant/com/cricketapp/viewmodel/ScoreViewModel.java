package app.cricket.prashant.com.cricketapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableInt;
import android.view.View;
import app.cricket.prashant.com.cricketapp.model.GetScore;

/**
 * Created by prashant on 2/12/17.
 */

public class ScoreViewModel extends BaseObservable {

    private GetScore scoreDetails;
    private Context context;


    public ScoreViewModel(GetScore getScore, Context context) {
        this.scoreDetails = getScore;
        this.context = context;

    }

    public String getMatchName() {
        return scoreDetails.teamOne + " Vs " + scoreDetails.teamSecond;
    }

    public String getMatchType() {
        return scoreDetails.type;
    }

    public String getScore() {
        return scoreDetails.score;
    }

    public String getIningRequirment() {
        return scoreDetails.inningsRequirement;
    }

    public void setmatch(GetScore getScore) {
        this.scoreDetails = getScore;
        notifyChange();
    }
}
