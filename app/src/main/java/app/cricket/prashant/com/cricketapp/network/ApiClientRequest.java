package app.cricket.prashant.com.cricketapp.network;

import java.util.concurrent.TimeUnit;

import app.cricket.prashant.com.cricketapp.BuildConfig;
import app.cricket.prashant.com.cricketapp.utils.ApiConstants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prashant on 2/11/17.
 */

public class ApiClientRequest {

    private static int READ_TIMEOUT = 15 ;
    private static int WRITE_TIMEOUT = 30;
    private static Retrofit.Builder retrofitBuilder;
    private static Retrofit mRetrofit;
    private static OkHttpClient client;

    public static <S> S createService(Class<S> serviceClass, boolean withGsonService) {
        client = createClient();
        mRetrofit = getRetrofitBuilder(withGsonService).client(client).build();
        return mRetrofit.create(serviceClass);
    }

    private static OkHttpClient createClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
        }
        return httpClient
                //add timeout
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private static Retrofit.Builder getRetrofitBuilder(boolean withGSONParsing) {
        if (retrofitBuilder == null) {
            if (withGSONParsing) {
                retrofitBuilder = new Retrofit.Builder()
                        .baseUrl(ApiConstants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
            } else {
                retrofitBuilder = new Retrofit.Builder()
                        .baseUrl(ApiConstants.BASE_URL);
            }
        }
        return retrofitBuilder;
    }

    /**
     * @return {@link Retrofit} object.
     */
    public static Retrofit retrofit() {
        return mRetrofit;
    }

    /**
     * @return {@link OkHttpClient} object.
     */
    public static OkHttpClient okHttpClient() {
        return client;
    }
}
