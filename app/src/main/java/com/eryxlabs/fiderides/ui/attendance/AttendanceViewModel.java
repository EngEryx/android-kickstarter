package com.eryxlabs.fiderides.ui.attendance;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.LinearLayout;

import com.eryxlabs.fiderides.models.Stream;
import com.eryxlabs.fiderides.ui.attendance.adapters.StreamsRecyclerViewAdapter;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ProgressDialog progressDialog;
    private List<Stream> ridesList =  new ArrayList<>();
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private StreamsRecyclerViewAdapter mStreamsRecyclerViewAdapter;

    public AttendanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No Classes Yet");
    }

    public LiveData<String> getText() {
        return mText;
    }
}