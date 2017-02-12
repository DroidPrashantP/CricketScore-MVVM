package app.cricket.prashant.com.cricketapp.utils;

import android.content.Context;

import java.io.IOException;
import java.lang.annotation.Annotation;

import app.cricket.prashant.com.cricketapp.network.ApiClientRequest;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by prashant on 11/02/17.
 */
public class RetrofitUtils {
    public static RetrofitError parseError(Response<?> response) {
        Converter<ResponseBody, RetrofitError> converter =
                ApiClientRequest.retrofit()
                        .responseBodyConverter(RetrofitError.class, new Annotation[0]);
        RetrofitError error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new RetrofitError();
        }
        return error;
    }

    public static void handleErrorCodes(Context context, Response<?> response, int requestMode) {
        if (response != null) {

            switch (response.code()) {
                case ApiConstants.ErrorCodes.NO_CONTENT:
                    break;

                case ApiConstants.ErrorCodes.UN_AUTHORIZED:
                    break;

                case ApiConstants.ErrorCodes.UN_PROCESSABLE:
                    break;

                case ApiConstants.ErrorCodes.PAGE_NOT_FOUND:
                    break;
            }

        } else {
            RetrofitError error = new RetrofitError(0, "Something wrong");

        }
    }
}