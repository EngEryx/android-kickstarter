package com.eryxlabs.fiderides.services;

import com.eryxlabs.fiderides.models.UserToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {

    @FormUrlEncoded
    @POST("login")
    Call<UserToken> getLoginToken(@Field("username") String username, @Field("password") String password, @Field("login_type") int login_type);

}
