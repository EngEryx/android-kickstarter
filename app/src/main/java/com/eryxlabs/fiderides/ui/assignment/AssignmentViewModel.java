package com.eryxlabs.fiderides.ui.assignment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class AssignmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AssignmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home learning activities fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}