package com.eryxlabs.fiderides.ui.travel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.eryxlabs.fiderides.models.Route;
import com.eryxlabs.fiderides.models.TravelRecord;
import com.eryxlabs.fiderides.repositories.TravelRepository;
import com.eryxlabs.fiderides.utils.NetworkResponse;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TravelRepository travelRepository;
    public MutableLiveData<List<TravelRecord>> records;
    public MutableLiveData<List<Route>> routes;
    public MutableLiveData<NetworkResponse> monitor;

    public TripViewModel(@NonNull Application application) {
        super(application);
        travelRepository = new TravelRepository(application);
        records = travelRepository.travelRecords;
        routes = travelRepository.routeRecords;
        monitor = travelRepository.monitor;
    }

    public void loadTripRoutes(int trip_id) {
        travelRepository.loadTravelRoutes(trip_id);
    }
}