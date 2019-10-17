package com.eryxlabs.fiderides.ui.assignment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.eryxlabs.fiderides.models.Assignment;
import com.eryxlabs.fiderides.repositories.AssignmentRepository;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

public class AssignmentViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private AssignmentRepository assignmentRepository;
    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<List<Assignment>> assignments;
    public AssignmentViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is home learning activities fragment");

        assignmentRepository=new AssignmentRepository(application);
        monitor=assignmentRepository.monitor;
        assignments=assignmentRepository.allAssignments;

    }


    public void getAllAssignmentsOnline(){

        assignmentRepository.getAllAssignentsOnline();
    }
    public LiveData<String> getText() {
        return mText;
    }
}