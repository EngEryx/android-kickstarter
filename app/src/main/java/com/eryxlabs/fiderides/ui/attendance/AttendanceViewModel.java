package com.eryxlabs.fiderides.ui.attendance;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class AttendanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AttendanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is attendance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}