package singh.harmeet.com.searchproject1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import singh.harmeet.com.searchproject1.MainApplication;
import singh.harmeet.com.searchproject.R;
import singh.harmeet.com.searchproject1.data.model.Event;
import singh.harmeet.com.searchproject1.data.model.Performer;
import singh.harmeet.com.searchproject1.di.component.DaggerDetailActivityComponent;
import singh.harmeet.com.searchproject1.di.component.DetailActivityComponent;
import singh.harmeet.com.searchproject1.di.module.DetailActivityModule;
import singh.harmeet.com.searchproject1.presenter.DetailViewPresenter;
import singh.harmeet.com.searchproject1.util.AppUtil;
import singh.harmeet.com.searchproject1.util.CircleTransform;
import singh.harmeet.com.searchproject1.util.Constants;
import singh.harmeet.com.searchproject1.view.DetailView;

/**
 * Created by harmeet.assi on 4/13/2018.
 */

//this activity will have details for the event and user can favorite the event .
public class DetailActivity extends AppCompatActivity implements DetailView{

    @BindView(R.id.image_venue_detail)
    ImageView image_venue;
    @BindView(R.id.title_detail)
    TextView title;
    @BindView(R.id.location_detail)
    TextView location;
    @BindView(R.id.date_time_detail)
    TextView date_time;
    @BindView(R.id.menu_tool)
    Toolbar menu_tool;

    private Menu menu;

    //the flag will keep the track of the state of the favorite icon
    private Boolean flag = false;

    private Picasso picasso;

    @Inject
    DetailViewPresenter detailViewPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailActivityComponent detailActivityComponent = DaggerDetailActivityComponent.builder().detailActivityModule(new DetailActivityModule(this)).appComponent(MainApplication.get(this).getAppComponent()).build();
        detailActivityComponent.injectDetailActivity(this);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        setSupportActionBar(menu_tool);

        picasso = MainApplication.get(this).getAppComponent().picasso();
        detailViewPresenter.attachView(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        if(savedInstanceState!=null){
//            flag = savedInstanceState.getBoolean(Constants.rotation);
//        }
        detailViewPresenter.loadEvent((Event)getIntent().getParcelableExtra(Constants.pass));

    }

    //called to make changes to the toolbar icons
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(flag) {
            if (menu != null) {
                MenuItem menuItem = menu.findItem(R.id.favorite_menu);
                if (menuItem != null) {
                    menuItem.setIcon(R.drawable.ic_pink_icon);
                }
            }
        }else{
            if(menu!=null) {
                menu.findItem(R.id.favorite_menu).setIcon(getResources().getDrawable(R.drawable.fav_icon_white));
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_search,menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite_menu:
                detailViewPresenter.addtoFav((Event) getIntent().getParcelableExtra(Constants.pass));
                break;
            case R.id.setting_menu:
                AppUtil.showAlertMessage(getString(R.string.favorite_string), this);
                break;
            default:
                break;
        }
            return super.onOptionsItemSelected(item);

    }

    //used this method to display favorite icon with pink color
    @Override
    public void showFavorite() {
        flag = true;
        invalidateOptionsMenu();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean(Constants.rotation,flag);
//    }


    @Override
    public void showError() {
        AppUtil.showAlertMessage("There was some error ,please try later",this);
    }

    @Override
    public void showItems() {

    }

    @Override
    public void hideFav() {
        flag = false;
        invalidateOptionsMenu();
    }

    @Override
    public void loadItems(Event event) {
        getSupportActionBar().setTitle(event.title());
        title.setText(event.title());
        location.setText(event.venue().display_location());
        date_time.setText(AppUtil.convertDate(event.datetime_local()));
        if(event.performers().size()>0){
            for(Performer performer:event.performers())
                if(performer.images().huge()!=null){
                    picasso.load(performer.images().huge()).transform(new CircleTransform()).into(image_venue);
                    break;
                }else{
                    //display broken image icon in case no image is present
                    picasso.load(R.drawable.broken_place).transform(new CircleTransform()).into(image_venue);

                }
        }
    }
}
