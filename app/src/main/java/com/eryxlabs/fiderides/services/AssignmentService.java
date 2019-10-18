package com.eryxlabs.fiderides.services;

import com.eryxlabs.fiderides.models.Assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AssignmentService {

    @GET("teacher/assignments/all")
    Call<List<Assignment>> getAllAssignments();
}
