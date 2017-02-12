package app.cricket.prashant.com.cricketapp.network;

import app.cricket.prashant.com.cricketapp.model.GetScore;
import app.cricket.prashant.com.cricketapp.model.Matches;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by prashant on 2/11/17.
 */

public interface ApiManager {
    @GET Call<Matches> fetchMatches(@Url String url);
    @GET Call<GetScore> fetchMatchScore(@Url String url);
}
