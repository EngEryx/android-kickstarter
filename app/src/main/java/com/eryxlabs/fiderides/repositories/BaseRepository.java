package com.eryxlabs.fiderides.repositories;

import android.app.Application;
import android.preference.PreferenceManager;

public class BaseRepository {
    protected Application application;

    public String getToken(){
        return  PreferenceManager.getDefaultSharedPreferences(application).getString("ACCESS_TOKEN", null);
    }
}
