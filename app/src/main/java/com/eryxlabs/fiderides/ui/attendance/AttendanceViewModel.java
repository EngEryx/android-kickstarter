package com.eryxlabs.fiderides.ui.attendance;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.eryxlabs.fiderides.models.Stream;

import java.util.ArrayList;
import java.util.List;

public class AttendanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private List<Stream> streamsList =  new ArrayList<>();

    public AttendanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No Classes Yet");
    }

    public LiveData<String> getText() {
        return mText;
    }
}