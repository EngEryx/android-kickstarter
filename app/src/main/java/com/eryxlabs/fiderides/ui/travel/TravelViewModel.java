package com.eryxlabs.fiderides.ui.travel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class TravelViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TravelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is travel attendance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}