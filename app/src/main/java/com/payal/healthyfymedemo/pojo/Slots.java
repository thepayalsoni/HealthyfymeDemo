package com.payal.healthyfymedemo.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

/**
 * Created by payal on 23/4/16.
 */
public class Slots {

    @SerializedName("slots")
    private LinkedHashMap<String,Sessions> dates;

    public LinkedHashMap<String, Sessions> getDates() {
        return dates;
    }

    public void setDates(LinkedHashMap<String, Sessions> dates) {
        this.dates = dates;
    }


}
