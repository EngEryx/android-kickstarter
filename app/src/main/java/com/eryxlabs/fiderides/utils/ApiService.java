package com.eryxlabs.fiderides.utils;

import com.eryxlabs.fiderides.models.Stream;
import com.eryxlabs.fiderides.models.User;
import com.eryxlabs.fiderides.models.UserToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<UserToken> getLoginToken(@Field("username") String username, @Field("password") String password, @Field("login_type") int login_type);

    @Headers({
            "Accept:application/json",
            "AUTH:API"
    })
    @GET("user")
    Call<User>getUser();

    @Headers({
            "Accept:application/json",
            "AUTH:API"
    })
    @GET("teacher/attendance/class-list")
    Call<Stream>getStreams();
}
