package com.payal.healthyfymedemo.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by payal on 23/4/16.
 */
public class Sessions implements Serializable {



    @SerializedName("morning")
    private ArrayList<SessionDetails> morningSessionArray;
    @SerializedName("afternoon")
    private ArrayList<SessionDetails> afternoonSessionArray;
    @SerializedName("evening")
    private ArrayList<SessionDetails> eveningSessionArray;

    public ArrayList<SessionDetails> getMorningSessionArray() {
        return morningSessionArray;
    }

    public void setMorningSessionArray(ArrayList<SessionDetails> morningSessionArray) {
        this.morningSessionArray = morningSessionArray;
    }

    public ArrayList<SessionDetails> getAfternoonSessionArray() {
        return afternoonSessionArray;
    }

    public void setAfternoonSessionArray(ArrayList<SessionDetails> afternoonSessionArray) {
        this.afternoonSessionArray = afternoonSessionArray;
    }

    public ArrayList<SessionDetails> getEveningSessionArray() {
        return eveningSessionArray;
    }

    public void setEveningSessionArray(ArrayList<SessionDetails> eveningSessionArray) {
        this.eveningSessionArray = eveningSessionArray;
    }

}
