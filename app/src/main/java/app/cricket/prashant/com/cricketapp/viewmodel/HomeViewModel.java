package app.cricket.prashant.com.cricketapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import app.cricket.prashant.com.cricketapp.R;
import app.cricket.prashant.com.cricketapp.model.GetData;
import app.cricket.prashant.com.cricketapp.network.ApiClientRequest;
import app.cricket.prashant.com.cricketapp.network.ApiManager;
import app.cricket.prashant.com.cricketapp.model.Matches;
import app.cricket.prashant.com.cricketapp.utils.ApiConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prashant on 2/12/17.
 */

public class HomeViewModel extends Observable {
    public ObservableInt mProgressBar;
    public ObservableInt mRecyclerView;
    public ObservableInt mLabel;
    public ObservableField<String> messageLabel;

    private List<GetData> mMatchList;
    private Context context;

    public HomeViewModel(@NonNull Context context) {

        this.context = context;
        this.mMatchList = new ArrayList<>();
        mProgressBar = new ObservableInt(View.GONE);
        mRecyclerView = new ObservableInt(View.GONE);
        mLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
    }

    public void fetchData() {
        showProgressBar();
        fetchMatchesData();
    }

    public void showProgressBar() {
        mLabel.set(View.GONE);
        mRecyclerView.set(View.GONE);
        mProgressBar.set(View.VISIBLE);
    }
    public void hideProgress() {
        mLabel.set(View.GONE);
        mRecyclerView.set(View.VISIBLE);
        mProgressBar.set(View.GONE);
    }

    public void fetchMatchesData() {
        String url = ApiConstants.Urls.FETCH_CURRENT_MATCHES;
        ApiManager apiManager = ApiClientRequest.createService(ApiManager.class, true);
        Call<Matches> apiCall = apiManager.fetchMatches(url);
        apiCall.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Matches matches = response.body();
                Log.e("Response",""+response.body().toString());
                if (response.isSuccessful()) {
                    if (response.code() == ApiConstants.ErrorCodes.NO_CONTENT) {
                        // no content
                    }else {
                        hideProgress();
                        setMatchesList(matches.getMatchesList());
                    }
                }
                if (response.code() == ApiConstants.ErrorCodes.UN_PROCESSABLE) {
                    try {
                        String string = response.errorBody().string();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.e("Response","Error");
                messageLabel.set(context.getString(R.string.error_loading_people));
                mProgressBar.set(View.GONE);
                mLabel.set(View.VISIBLE);
                mRecyclerView.set(View.GONE);
            }
        });
    }


    private void setMatchesList(List<GetData> peoples) {
        mMatchList.addAll(peoples);
        setChanged();
        notifyObservers();
    }

    public List<GetData> getMatchList() {
        return mMatchList;
    }


}
