package com.eryxlabs.fiderides.utils;

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
}
