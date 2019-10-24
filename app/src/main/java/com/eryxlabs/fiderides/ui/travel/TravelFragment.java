package com.eryxlabs.fiderides.ui.travel;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Trip;
import com.eryxlabs.fiderides.ui.travel.adapters.TripsRecyclerViewAdapter;
import com.eryxlabs.fiderides.ui.travel.trip.TripActivity;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;

public class TravelFragment extends Fragment implements TripsRecyclerViewAdapter.TripsRecyclerViewAdapterInterface {

    private TravelViewModel travelViewModel;
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private TripsRecyclerViewAdapter mTripsRecyclerViewAdapter;
    private ProgressBar pb;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        travelViewModel = ViewModelProviders.of(this).get(TravelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_travel, container, false);
        mTripsRecyclerViewAdapter =  new TripsRecyclerViewAdapter(getContext(),this);

        pb = root.findViewById(R.id.pb_travel);
        recyclerView =  root.findViewById(R.id.travel_recycler_view);
        emptyView = root.findViewById(R.id.travel_empty_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(mTripsRecyclerViewAdapter);

        travelViewModel.loadTrips();
        travelViewModel.trips.observe(this, trips -> {
            mTripsRecyclerViewAdapter.setData(trips);
        });

        travelViewModel.monitor.observe(this, networkResponse -> {
            if (networkResponse!=null){
                if (networkResponse.is_loading){
                    pb.setVisibility(View.VISIBLE);
                }else{
                    pb.setVisibility(View.GONE);
                }
            }
        });

        return root;
    }

    @Override
    public void openRoutes(String kind, Trip trip) {
        Intent intent = new Intent(getContext(), TripActivity.class);
        intent.putExtra("KIND", kind);
        intent.putExtra("TRIP", trip);
        startActivity(intent);
    }
}