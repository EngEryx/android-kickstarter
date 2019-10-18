package com.eryxlabs.fiderides.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;


import com.eryxlabs.fiderides.models.Success;
import com.eryxlabs.fiderides.models.TravelRecord;
import com.eryxlabs.fiderides.utils.ApiService;
import com.eryxlabs.fiderides.utils.CoreUtils;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelRepository extends BaseRepository {

    public MutableLiveData<List<TravelRecord>> travelRecords;
    public MutableLiveData<NetworkResponse> monitor;


    public TravelRepository(Application application){
        this.application = application;
        travelRecords = new MutableLiveData<>();
        monitor=new MutableLiveData<>();


    }

    public void loadTravelStudents(String kind,int road_id){

        monitor.postValue(new NetworkResponse(true));

        Call<List<TravelRecord>> call = CoreUtils.getAuthRetrofitClient(getToken()).create(ApiService.class).getTravelRecords(road_id,kind);
        call.enqueue(new Callback<List<TravelRecord>>() {
            @Override
            public void onResponse(Call<List<TravelRecord>> call, Response<List<TravelRecord>> response) {
                if(response.isSuccessful()){
                    travelRecords.postValue(response.body());
                    monitor.postValue(new NetworkResponse(false));

                }else{
                    monitor.postValue(new NetworkResponse(false,"An error was encountered",response.code()));

                }
            }

            @Override
            public void onFailure(Call<List<TravelRecord>> call, Throwable t) {
                monitor.postValue(new NetworkResponse(false,"An network error was encountered",505));

            }
        });
    }

    public void updateTravelRecords(List<TravelRecord> records) {
        monitor.postValue(new NetworkResponse(true));

        Call<Success> call = CoreUtils.getAuthRetrofitClient(getToken()).create(ApiService.class).updateTravelRecords(records);
        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                if(response.isSuccessful()){
                    monitor.postValue(new NetworkResponse(false,"Updated",203));
                }else{
                    monitor.postValue(new NetworkResponse(false,"An error was encountered",response.code()));

                }
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                monitor.postValue(new NetworkResponse(false,"An network error was encountered",505));

            }
        });
    }
}
