package com.eryxlabs.fiderides.ui.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.eryxlabs.fiderides.repositories.AuthorizationRepository;
import com.eryxlabs.fiderides.utils.NetworkResponse;

public class LoginViewModel extends AndroidViewModel {


    AuthorizationRepository authorizationRepository;
    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<String> accessTokenLogin;
    public LoginViewModel(@NonNull Application application) {
        super(application);

        authorizationRepository=new AuthorizationRepository(application);
        monitor=authorizationRepository.monitor;
        accessTokenLogin=authorizationRepository.accessTokenLogin;
    }


    public void attemptLogin(String username,String password,int userType){

        authorizationRepository.attemptLogin(username, password, userType);
    }
}
