package com.eryxlabs.fiderides.services;

import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.models.Assignment;
import com.eryxlabs.fiderides.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AssessmentService {

    @GET("teacher/assessment/all")
    Call<List<Assessment>> getAllAsssessments();

    @FormUrlEncoded
    @POST("teacher/assessment/results")
    Call<List<Result>> getAssessmentResults(@Field("assessment_id") int assessmentId);



    @FormUrlEncoded
    @POST("teacher/assessment/result/update")
    Call<Void> updateResults(@Field("id") int id,@Field("description") String description, @Field("grade") int grade);
}
