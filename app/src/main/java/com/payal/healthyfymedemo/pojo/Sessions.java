package com.payal.healthyfymedemo.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by payal on 23/4/16.
 */
public class Sessions {

    @SerializedName("morning")
    private ArrayList<Morning> morningSessionArray;
    @SerializedName("afternoon")
    private ArrayList<Afternoon> afternoonSessionArray;
    @SerializedName("evening")
    private ArrayList<Evening> eveningSessionArray;

    public ArrayList<Morning> getMorningSessionArray() {
        return morningSessionArray;
    }

    public void setMorningSessionArray(ArrayList<Morning> morningSessionArray) {
        this.morningSessionArray = morningSessionArray;
    }

    public ArrayList<Afternoon> getAfternoonSessionArray() {
        return afternoonSessionArray;
    }

    public void setAfternoonSessionArray(ArrayList<Afternoon> afternoonSessionArray) {
        this.afternoonSessionArray = afternoonSessionArray;
    }

    public ArrayList<Evening> getEveningSessionArray() {
        return eveningSessionArray;
    }

    public void setEveningSessionArray(ArrayList<Evening> eveningSessionArray) {
        this.eveningSessionArray = eveningSessionArray;
    }


}
