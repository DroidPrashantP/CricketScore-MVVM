package app.cricket.prashant.com.cricketapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import app.cricket.prashant.com.cricketapp.model.GetData;
import app.cricket.prashant.com.cricketapp.utils.ApiConstants;
import app.cricket.prashant.com.cricketapp.view.matches.ScoreDetailsActivity;

/**
 * Created by prashant on 2/12/17.
 */

public class ItemViewModel extends BaseObservable {

    private GetData match;
    private Context context;

    public ItemViewModel(GetData match, Context context) {
        this.match = match;
        this.context = context;
    }

    public String getTeamOne() {
        return match.team1 + " Vs " + match.team2;
    }

    public String getTeamTwo() {
        return match.team2;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getMatchDate() {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'T' hh:mm:ss", Locale.getDefault());
            date = simpleDateFormat.parse(match.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.toString();
    }

    public void onItemClick(View view) {
        context.startActivity(new Intent(context, ScoreDetailsActivity.class).putExtra(ApiConstants.Params.MATCH_UNIQUE_KEY, match.unique_id));
    }

    public void setmatch(GetData match) {
        this.match = match;
        notifyChange();
    }
}
