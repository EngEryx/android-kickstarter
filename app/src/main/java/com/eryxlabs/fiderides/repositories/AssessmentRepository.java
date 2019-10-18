package com.eryxlabs.fiderides.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.models.Assignment;
import com.eryxlabs.fiderides.models.Result;
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
    public MutableLiveData<List<Result>> assessmentResults;
    public AssessmentRepository(Application application){

        this.application=application;
        monitor=new MutableLiveData<>();
        assessments=new MutableLiveData<>();
        assessmentResults=new MutableLiveData<>();

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


    public void getStudentRecordsOnline(int assessmentId) {
        Call<List<Result>> call=CoreUtils.getAuthRetrofitClient(getToken()).create(AssessmentService.class).getAssessmentResults(assessmentId);
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                monitor.postValue(new NetworkResponse(false));

                if (response.isSuccessful() && response.body()!=null){

                    assessmentResults.postValue(response.body());



                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                try{
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",((HttpException) t).code()));
                }catch (Exception e){
                    monitor.postValue(new NetworkResponse(false,"Check your internet connection then try again",0));
                }
            }
        });


    }

    public void updateGradesOnline(Result result) {

        Log.e("results_results",result.getDescription() + result.getGrade());
//        Call<Void> call=CoreUtils.getAuthRetrofitClient(getToken()).create(AssessmentService.class).

    }
}
