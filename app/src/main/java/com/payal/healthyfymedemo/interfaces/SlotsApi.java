package com.payal.healthyfymedemo.interfaces;

import com.payal.healthyfymedemo.pojo.Slots;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by payal on 23/4/16.
 */
public interface SlotsApi  {

    @GET("/api/v1/booking/slots/all?username=alok%40x.coz&api_key=a4aeb4e27f27b5786828f6cdf00d8d2cb44fe6d7&vc=276&expert_username=neha%40healthifyme.com&format=json\n")
    Call<Slots> loadSlots();
}
