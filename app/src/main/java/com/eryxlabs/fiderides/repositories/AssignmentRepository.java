package com.eryxlabs.fiderides.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.eryxlabs.fiderides.models.Assignment;
import com.eryxlabs.fiderides.services.AssignmentService;
import com.eryxlabs.fiderides.utils.CoreUtils;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class AssignmentRepository extends BaseRepository {

    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<List<Assignment>> allAssignments;
    public AssignmentRepository(Application application){
           this.application=application;
            monitor=new MutableLiveData<>();
            allAssignments=new MutableLiveData<>();

    }

    public void getAllAssignentsOnline(){
        Call<List<Assignment>> call= CoreUtils.getAuthRetrofitClient(getToken()).create(AssignmentService.class).getAllAssignments();
        call.enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
              monitor.postValue(new NetworkResponse(false));
                if (response.body()!=null && response.isSuccessful()){
                    allAssignments.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });

    }


}
