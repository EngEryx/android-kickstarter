package com.eryxlabs.fiderides.utils;

import android.content.Intent;

import com.eryxlabs.fiderides.models.BookStatus;
import com.eryxlabs.fiderides.models.Ride;
import com.eryxlabs.fiderides.models.UserToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<UserToken> getLoginToken(@Field("username") String username, @Field("password") String password);

    @Headers({
            "Accept:application/json",
            "AUTH:API"
    })
    @GET("rides")
    Call<List<Ride>> getRides();

    @Headers({
            "Accept:application/json",
            "AUTH:API"
    })
    @FormUrlEncoded
    @POST("rides/ride")
    Call<BookStatus> bookRide(@Field("destination_id") int destination_id,
                              @Field("ride_id") int ride_id,
                              @Field("start_id") int start_id);
    @Headers({
            "Accept:application/json",
            "AUTH:API"
    })
    @FormUrlEncoded
    @POST("rides/verify")
    Call<BookStatus> confirmRide(@Field("ride_id") int ride_id);
}
