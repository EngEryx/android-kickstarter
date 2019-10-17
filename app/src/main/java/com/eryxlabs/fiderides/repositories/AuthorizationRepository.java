package com.eryxlabs.fiderides.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.preference.PreferenceManager;

import com.eryxlabs.fiderides.models.User;
import com.eryxlabs.fiderides.models.UserToken;
import com.eryxlabs.fiderides.services.AuthService;
import com.eryxlabs.fiderides.utils.CoreUtils;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class AuthorizationRepository extends BaseRepository {

    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<String> accessTokenLogin;
    public AuthorizationRepository(Application application){

        this.application=application;
        monitor=new MutableLiveData<>();
        accessTokenLogin=new MutableLiveData<>();
    }

    public void attemptLogin(String username,String password,int loginType){
        Call<UserToken> call= CoreUtils.getRetrofitClient().create(AuthService.class).getLoginToken(username,password,loginType);
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
            monitor.postValue(new NetworkResponse(false));

                if (response.body()!=null){

                    saveToken(response.body().authToken);
                    accessTokenLogin.postValue(response.body().authToken);

                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });

    }


    public void saveToken(String token){
        PreferenceManager.getDefaultSharedPreferences(application).edit().putString("ACCESS_TOKEN", token).apply();
    }
}
