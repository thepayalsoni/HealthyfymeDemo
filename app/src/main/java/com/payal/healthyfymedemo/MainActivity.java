package com.payal.healthyfymedemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.payal.healthyfymedemo.adapter.DateAdapter;
import com.payal.healthyfymedemo.interfaces.SlotsApi;
import com.payal.healthyfymedemo.pojo.Slots;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<Slots> {
    ViewPager vp_dates;
    TextView tv_month;
    private static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";
    TabLayout tabs;

    private static final String FRAGMENT_LIST_VIEW = "payview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://108.healthifyme.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        SlotsApi stackOverflowAPI = retrofit.create(SlotsApi.class);

        Call<Slots> call = stackOverflowAPI.loadSlots();
        call.enqueue(this);

        vp_dates = (ViewPager) findViewById(R.id.vp_dates);
        tv_month = (TextView) findViewById(R.id.tv_month);

        tabs = (TabLayout) findViewById(R.id.tabs);

        if (savedInstanceState == null) {

        }

    }


    @Override
    public void onResponse(Response<Slots> response, Retrofit retrofit) {

        Log.v("response", "+++++" + response);

        vp_dates.setAdapter(new DateAdapter(getSupportFragmentManager(), response.body().getDates()));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setupWithViewPager(vp_dates);


        getSupportFragmentManager().beginTransaction()
                .add(new ExampleExpandableDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER)
                .commit();
        /*getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ExpandableExampleFragment(), FRAGMENT_LIST_VIEW)
                .commit();*/

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    public AbstractExpandableDataProvider getDataProvider() {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DATA_PROVIDER);
        return ((ExampleExpandableDataProviderFragment) fragment).getDataProvider();
    }

}
