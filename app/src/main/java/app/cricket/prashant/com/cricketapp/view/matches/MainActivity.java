package app.cricket.prashant.com.cricketapp.view.matches;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Observable;
import java.util.Observer;

import app.cricket.prashant.com.cricketapp.R;
import app.cricket.prashant.com.cricketapp.databinding.ActivityMainBinding;
import app.cricket.prashant.com.cricketapp.viewmodel.HomeViewModel;

public class MainActivity extends AppCompatActivity implements Observer {
    private ActivityMainBinding mMainActivityBinding;
    private HomeViewModel mHomeViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(mMainActivityBinding.toolbar);
        setupListPeopleView(mMainActivityBinding.listPeople);
        setupObserver(mHomeViewModel);
        mHomeViewModel.fetchData();

        mSwipeRefreshLayout = mMainActivityBinding.swipeRefreshLayout;
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mHomeViewModel.fetchData();
            }
        });
    }

    private void initDataBinding() {
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mHomeViewModel = new HomeViewModel(this);
        mMainActivityBinding.setMainViewModel(mHomeViewModel);
        mHomeViewModel.fetchData();
    }


    private void setupListPeopleView(RecyclerView listPeople) {
        MatchAdapter adapter = new MatchAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // mHomeViewModel.reset();
    }


    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof HomeViewModel) {
            MatchAdapter peopleAdapter = (MatchAdapter) mMainActivityBinding.listPeople.getAdapter();
            HomeViewModel homeViewModel = (HomeViewModel) observable;
            peopleAdapter.setMatchList(homeViewModel.getMatchList());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void disableSwipeRefreshView(){
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
