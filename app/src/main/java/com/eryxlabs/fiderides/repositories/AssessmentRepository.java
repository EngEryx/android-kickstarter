package com.eryxlabs.fiderides.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.models.Assignment;
import com.eryxlabs.fiderides.services.AssessmentService;
import com.eryxlabs.fiderides.utils.CoreUtils;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class AssessmentRepository extends BaseRepository{

    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<List<Assessment>> assessments;

    public AssessmentRepository(Application application){

        this.application=application;
        monitor=new MutableLiveData<>();
        assessments=new MutableLiveData<>();

    }

    public void getAllAssessments(){

        Call<List<Assessment>> call= CoreUtils.getAuthRetrofitClient(getToken()).create(AssessmentService.class).getAllAsssessments();
        call.enqueue(new Callback<List<Assessment>>() {
            @Override
            public void onResponse(Call<List<Assessment>> call, Response<List<Assessment>> response) {
                monitor.postValue(new NetworkResponse(false));
                if (response.isSuccessful() && response.body()!=null){

                    assessments.postValue(response.body());


                }
            }

            @Override
            public void onFailure(Call<List<Assessment>> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });
    }


}
