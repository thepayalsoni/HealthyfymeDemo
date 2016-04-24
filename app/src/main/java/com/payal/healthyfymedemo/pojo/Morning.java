package com.payal.healthyfymedemo.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by payal on 23/4/16.
 */
public class Morning {

    @SerializedName("end_time")
    private String endTime;
    @SerializedName("is_booked")
    private String isBooked;
    @SerializedName("is_expired")
    private String isExpired;
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

    public String getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(String isBooked) {
        this.isBooked = isBooked;
    }

    public String getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(String isExpired) {
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
