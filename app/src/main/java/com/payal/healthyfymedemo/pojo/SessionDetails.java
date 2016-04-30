package com.payal.healthyfymedemo.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by payal on 23/4/16.
 */
public class SessionDetails implements Serializable {

    @SerializedName("end_time")
    private String endTime;
    @SerializedName("is_booked")
    private boolean isBooked;
    @SerializedName("is_expired")
    private boolean isExpired;
    @SerializedName("slot_id")
    private long slotId;
    @SerializedName("start_time")
    private String startTime;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
