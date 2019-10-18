package com.eryxlabs.fiderides.ui.assessment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.models.Result;
import com.eryxlabs.fiderides.repositories.AssessmentRepository;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

public class AssessmentViewModel extends AndroidViewModel {

    AssessmentRepository assessmentRepository;
    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<List<Assessment>> assessments;
    public MutableLiveData<List<Result>> assessmentResults;
    public AssessmentViewModel(@NonNull Application application) {
        super(application);

        assessmentRepository=new AssessmentRepository(application);
        monitor=assessmentRepository.monitor;
        assessments=assessmentRepository.assessments;
        assessmentResults=assessmentRepository.assessmentResults;
    }

    public void getAllAssessmentsOnline(){

        assessmentRepository.getAllAssessments();
    }

    public void getStudentRecordsOnline(int assessmentId) {
        assessmentRepository.getStudentRecordsOnline(assessmentId);
    }

}
