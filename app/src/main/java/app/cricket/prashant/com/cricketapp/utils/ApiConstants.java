package app.cricket.prashant.com.cricketapp.utils;

/**
 * Created by prashant on 2/11/17.
 */

public class ApiConstants {

    public final static String BASE_URL = "http://cricapi.com/api/";
    public final static String API_KEY="apikey=q27cVtIh24ehRngvtK2EnHJaLvG3";

    public interface Urls {
        String FETCH_CURRENT_MATCHES = BASE_URL + "matches?"+API_KEY;
        String FETCH_CURRENT_MATCHES_SCORE = BASE_URL + "cricketScore?"+API_KEY+"&unique_id=";
    }

    public interface Params {
        String API_KEY = "apikey";
        String MATCH_UNIQUE_KEY = "unique_id";
    }

    public interface ErrorCodes {
        int SUCCESS = 200;
        int NO_CONTENT = 204;
        int UN_AUTHORIZED = 401;
        int UN_PROCESSABLE = 422;
        int PAGE_NOT_FOUND = 404;

    }

}
