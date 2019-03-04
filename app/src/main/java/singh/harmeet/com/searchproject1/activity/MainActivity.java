package singh.harmeet.com.searchproject1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import singh.harmeet.com.searchproject1.MainApplication;
import singh.harmeet.com.searchproject.R;
import singh.harmeet.com.searchproject1.adapter.EventAdapter;
import singh.harmeet.com.searchproject1.data.model.Event;
import singh.harmeet.com.searchproject1.di.component.DaggerMainActivityComponent;
import singh.harmeet.com.searchproject1.di.component.MainActivityComponent;
import singh.harmeet.com.searchproject1.di.module.MainActivityModule;
import singh.harmeet.com.searchproject1.presenter.MainViewPresenter;
import singh.harmeet.com.searchproject1.util.AppUtil;
import singh.harmeet.com.searchproject1.util.Constants;
import singh.harmeet.com.searchproject1.util.ItemClickListener;
import singh.harmeet.com.searchproject1.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView,ItemClickListener{

    @Inject
    EventAdapter eventAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.search_tool)
    Toolbar tool_search;

    @BindView(R.id.relative_text)
    RelativeLayout relativeLayout;

    SearchView searchView;

//    private ArrayList<Event> events;

    @Inject
    MainViewPresenter mainViewPresenter;

    Menu search;

    private
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).appComponent(MainApplication.get(this).getAppComponent()).build();
        mainActivityComponent.injectMainActivity(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(tool_search);
        initView(savedInstanceState);
    }

    private void initView(Bundle bundle) {
        recyclerView.setAdapter(eventAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        eventAdapter.setItemClickListener(this);
        mainViewPresenter.attachView(this);
        mainViewPresenter.scrollEvents(RxRecyclerView.scrollEvents(recyclerView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_menu));
        mainViewPresenter.searchEvent(RxSearchView.queryTextChanges(searchView),AppUtil.hasInternet(this));
        return true;
    }

    @Override
    public void showResults(List<Event> result) {
        relativeLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        eventAdapter.setList(result);
//        events = new ArrayList<Event>();
//        events.addAll(result);
        eventAdapter.notifyDataSetChanged();
    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.rotation_main, events);
    }*/

    @Override
    public void Error() {
        AppUtil.showAlertMessage("There was some error ,please try later",this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewPresenter.detachView();
    }
    @Override
    public int itemsVisible() {
        return linearLayoutManager.getChildCount()+linearLayoutManager.findFirstVisibleItemPosition();
    }

    @Override
    public void InternetError() {
            AppUtil.showAlertMessage("Please check your internet connection and try later",this);
    }


    @Override
    public void onItemClick(Event event, int position) {
        AppUtil.showAlertMessage(event.title(),this);
        startIntent(event);

    }

    private void startIntent(Event event) {
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra(Constants.pass,event);
        startActivity(intent);
    }
}
