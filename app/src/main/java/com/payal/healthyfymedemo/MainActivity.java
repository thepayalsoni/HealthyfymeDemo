package com.payal.healthyfymedemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.payal.healthyfymedemo.adapter.DateAdapter;
import com.payal.healthyfymedemo.interfaces.SlotsApi;
import com.payal.healthyfymedemo.pojo.Sessions;
import com.payal.healthyfymedemo.pojo.Slots;
import com.payal.healthyfymedemo.utility.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Slots> {
    ViewPager vp_dates;
    TextView  tv_month;

    PagerTabStrip pager_tab_strip;
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

        vp_dates =(ViewPager) findViewById(R.id.vp_dates);
        tv_month = (TextView) findViewById(R.id.tv_month);


        pager_tab_strip = (PagerTabStrip) vp_dates.findViewById(R.id.pager_tab_strip);

        pager_tab_strip.setTabIndicatorColor(getResources().getColor(R.color.appRed));



    }


    @Override
    public void onResponse( Response<Slots> response, Retrofit retrofit) {

        Log.v("response", "+++++" + response);

        vp_dates.setAdapter(new DateAdapter(getSupportFragmentManager(), response.body().getDates()));



    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }







}
