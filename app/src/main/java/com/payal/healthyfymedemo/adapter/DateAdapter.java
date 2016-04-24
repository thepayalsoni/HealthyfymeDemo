package com.payal.healthyfymedemo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.payal.healthyfymedemo.DemoFragment;
import com.payal.healthyfymedemo.MainActivity;
import com.payal.healthyfymedemo.R;
import com.payal.healthyfymedemo.pojo.Sessions;
import com.payal.healthyfymedemo.utility.Utils;

import java.util.LinkedHashMap;

/**
 * Created by payal on 24/4/16.
 */
public class DateAdapter extends FragmentPagerAdapter {

    LinkedHashMap<String, Sessions> datesHashMap;
    Context mContext;
    String date;



    public DateAdapter(FragmentManager fm,LinkedHashMap<String, Sessions> datesHashMap) {
        super(fm);
        this.datesHashMap = datesHashMap ;
    }

    /*public DateAdapter(Context context, )
    {

        this.mContext = context;
    }*/


    public String getByIndex(LinkedHashMap<String, Sessions> hMap, int index){
        return (String) hMap.keySet().toArray()[index];
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return datesHashMap.size();
    }

    @Override
    public float getPageWidth(int position) {
        return(1.0f);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return(view == object);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        date =getByIndex(datesHashMap, position);

        return Utils.getDate(date)+"\n"+Utils.getDay(date);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new DemoFragment();

        // Attach some data to the fragment
        // that we'll use to populate our fragment layouts
        Bundle args = new Bundle();
        args.putInt("page_position", position + 1);

        // Set the arguments on the fragment
        // that will be fetched in the
        // DemoFragment@onCreateView
        fragment.setArguments(args);

        return fragment;
    }


}