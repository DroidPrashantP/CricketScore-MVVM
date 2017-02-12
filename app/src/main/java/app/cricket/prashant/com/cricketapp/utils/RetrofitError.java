package app.cricket.prashant.com.cricketapp.utils;

/**
 * Created by prashnt on 11/02/17.
 */

public class RetrofitError {
    private int statusCode;
    private String message;

    public RetrofitError() {
    }

    public RetrofitError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}