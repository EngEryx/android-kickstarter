package com.eryxlabs.fiderides.services;

import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.models.Assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AssessmentService {

    @GET("teacher/assessment/all")
    Call<List<Assessment>> getAllAsssessments();
}
