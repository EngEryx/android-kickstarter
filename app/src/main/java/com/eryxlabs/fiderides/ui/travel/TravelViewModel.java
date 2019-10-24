package com.eryxlabs.fiderides.ui.travel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.eryxlabs.fiderides.models.Success;
import com.eryxlabs.fiderides.models.TravelRecord;
import com.eryxlabs.fiderides.models.Trip;
import com.eryxlabs.fiderides.repositories.TravelRepository;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

public class TravelViewModel extends AndroidViewModel {

    private TravelRepository travelRepository;
    public MutableLiveData<List<TravelRecord>> records;
    public MutableLiveData<List<Trip>> trips;
    public MutableLiveData<NetworkResponse> monitor;
    public MutableLiveData<Success> savedAttendance;

    public TravelViewModel(@NonNull Application application) {
        super(application);
        travelRepository = new TravelRepository(application);
        records = travelRepository.travelRecords;
        trips = travelRepository.tripRecords;
        monitor = travelRepository.monitor;
        savedAttendance = travelRepository.attendanceSaved;
    }

    public void loadTravelStudents(int road_id){
        travelRepository.loadTravelStudents(road_id);
    }

    public void updateTravelRecords(List<TravelRecord> records) {
        travelRepository.updateTravelRecords(records);
    }

    public void loadTrips() {
        travelRepository.loadTravelTrips();
    }
}