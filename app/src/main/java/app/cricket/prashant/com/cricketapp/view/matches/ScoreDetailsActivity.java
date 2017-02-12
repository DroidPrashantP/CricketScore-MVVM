package app.cricket.prashant.com.cricketapp.view.matches;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.databinding.tool.DataBinder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.cricket.prashant.com.cricketapp.R;
import app.cricket.prashant.com.cricketapp.databinding.ActivityScoreDetailsBinding;
import app.cricket.prashant.com.cricketapp.model.GetScore;
import app.cricket.prashant.com.cricketapp.model.Matches;
import app.cricket.prashant.com.cricketapp.network.ApiClientRequest;
import app.cricket.prashant.com.cricketapp.network.ApiManager;
import app.cricket.prashant.com.cricketapp.utils.ApiConstants;
import app.cricket.prashant.com.cricketapp.viewmodel.ScoreViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreDetailsActivity extends AppCompatActivity {

    int mMatchUniqId;
    ActivityScoreDetailsBinding mActivityScoreDetailsBinding;
    SwipeRefreshLayout mSwipeRefreshLayout;
    public ProgressBar mProgressBar;
    public TextView mLabel;
    public LinearLayout mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityScoreDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_score_details);
        getExtrasFromIntent();


        mProgressBar = mActivityScoreDetailsBinding.progressPeople;
        mLabel = mActivityScoreDetailsBinding.labelStatus;
        mContentLayout = mActivityScoreDetailsBinding.contentLayout;

        mSwipeRefreshLayout = mActivityScoreDetailsBinding.swipeRefreshLayout;
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);

                fetchMatchScore();
            }
        });

        fetchMatchScore();
    }


    public void showProgressBar() {
        mLabel.setVisibility(View.GONE);
        mContentLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mLabel.setVisibility(View.GONE);
        mContentLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }


    private void fetchMatchScore() {
        showProgressBar();
        String url = ApiConstants.Urls.FETCH_CURRENT_MATCHES_SCORE + mMatchUniqId;
        ApiManager apiManager = ApiClientRequest.createService(ApiManager.class, true);
        Call<GetScore> apiCall = apiManager.fetchMatchScore(url);
        apiCall.enqueue(new Callback<GetScore>() {
            @Override
            public void onResponse(Call<GetScore> call, Response<GetScore> response) {
                hideProgress();
                // stopping swipe refresh
                mSwipeRefreshLayout.setRefreshing(false);
                GetScore scoreDetails = response.body();
                Log.e("Response", "" + response.body().toString());
                if (response.isSuccessful()) {
                    if (response.code() == ApiConstants.ErrorCodes.NO_CONTENT) {
                        // no content
                    } else {
                        setScoreDetails(scoreDetails);
                    }
                } else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<GetScore> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.e("Response", "Error");
                mProgressBar.setVisibility(View.GONE);
                mLabel.setVisibility(View.VISIBLE);
                mContentLayout.setVisibility(View.GONE);

            }
        });
    }

    private void setScoreDetails(GetScore scoreDetails) {
        ScoreViewModel scoreViewModel = new ScoreViewModel(scoreDetails, this);
        mActivityScoreDetailsBinding.setScoreDetails(scoreViewModel);
    }

    private void getExtrasFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mMatchUniqId = bundle.getInt(ApiConstants.Params.MATCH_UNIQUE_KEY);
        }
    }
}
