package com.payal.healthyfymedemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.payal.healthyfymedemo.MainActivity;
import com.payal.healthyfymedemo.fragment.AvailabilityFragment;
import com.payal.healthyfymedemo.pojo.Sessions;
import com.payal.healthyfymedemo.utility.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by payal on 24/4/16.
 */
public class DateAdapter extends FragmentPagerAdapter implements MainActivity.FetchMonth {

    LinkedHashMap<String, Sessions> datesHashMap;
    Context mContext;
    String date;
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public DateAdapter(FragmentManager fm, LinkedHashMap<String, Sessions> datesHashMap) {
        super(fm);
        this.datesHashMap = datesHashMap;
        MainActivity.fetchMonth= DateAdapter.this;
    }


    public String getByIndex(LinkedHashMap<String, Sessions> hMap, int index) {
        return (String) hMap.keySet().toArray()[index];
    }


    @Override
    public int getCount() {
        return datesHashMap.size();
    }

    @Override
    public float getPageWidth(int position) {
        return (1.0f);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        date = getByIndex(datesHashMap, position);

        return Utils.getDate(date) + "\n" + Utils.getDay(date);
    }


    @Override
    public Fragment getItem(int position) {

        Sessions session = getSelectedSession(datesHashMap,position);


        return AvailabilityFragment.newInstance(session);
    }

    private Sessions getSelectedSession(LinkedHashMap<String, Sessions> hMap,int position)
    {

       return  (Sessions)hMap.values().toArray()[position];
    }


    @Override
    public String getMonthData(int position)
    {
        return getByIndex(datesHashMap, position);
    }

}