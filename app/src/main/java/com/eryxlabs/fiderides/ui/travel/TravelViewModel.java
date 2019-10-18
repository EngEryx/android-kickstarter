package com.eryxlabs.fiderides.ui.travel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.eryxlabs.fiderides.models.TravelRecord;
import com.eryxlabs.fiderides.repositories.TravelRepository;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

public class TravelViewModel extends AndroidViewModel {

    private TravelRepository travelRepository;
    public MutableLiveData<List<TravelRecord>> records;
    public MutableLiveData<NetworkResponse> monitor;

    public TravelViewModel(@NonNull Application application) {
        super(application);
        travelRepository = new TravelRepository(application);
        records = travelRepository.travelRecords;
        monitor = travelRepository.monitor;
    }

    public void loadTravelStudents(String kind,int road_id){
        travelRepository.loadTravelStudents(kind,road_id);
    }

    public void updateTravelRecords(List<TravelRecord> records) {
        travelRepository.updateTravelRecords(records);
    }
}