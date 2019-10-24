package com.eryxlabs.fiderides.ui.travel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Trip;
import com.eryxlabs.fiderides.ui.travel.adapters.RoutesRecyclerViewAdapter;
import com.eryxlabs.fiderides.ui.travel.mark.TravelAttendanceActivity;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;


public class TripFragment extends Fragment implements RoutesRecyclerViewAdapter.RoutesRecyclerViewAdapterInterface {

    private TripViewModel tripViewModel;
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private RoutesRecyclerViewAdapter mRoutesRecyclerViewAdapter;
    private ProgressBar pb;
    private Trip mTrip;
    private String mKind;
    private Button mStartTrip;
    private Button mEndTrip;

    @SuppressLint("Range")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trip, container, false);
        mRoutesRecyclerViewAdapter =  new RoutesRecyclerViewAdapter(getContext(),this);

        pb = root.findViewById(R.id.pb_trip);
        recyclerView = root.findViewById(R.id.trip_recycler_view);
        emptyView = root.findViewById(R.id.trip_empty_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(mRoutesRecyclerViewAdapter);
        mStartTrip = root.findViewById(R.id.btnStartTrip);
        mEndTrip = root.findViewById(R.id.btnEndTrip);
        setTrip((Trip) getArguments().getSerializable("TRIP"));
        setKind(getArguments().getString("KIND"));

        tripViewModel.loadTripRoutes(mTrip.getId());
        tripViewModel.routes.observe(this, routes -> {
            mRoutesRecyclerViewAdapter.setData(routes);
        });
        tripViewModel.monitor.observe(this, networkResponse -> {
            if (networkResponse!=null){
                if (networkResponse.is_loading){
                    pb.setVisibility(View.VISIBLE);
                }else{
                    pb.setVisibility(View.GONE);
                }
            }
        });

        showMessage(""+mTrip.getId());

        mStartTrip.setOnClickListener(v -> {
            showMessage("Trip Started");
            mEndTrip.setEnabled(true);
            mEndTrip.setAlpha(1);
            mStartTrip.setEnabled(false);
            mStartTrip.setAlpha((float) 0.5);
            /*Todo:: start trip*/
        });

        mEndTrip.setOnClickListener(v -> {
            showMessage("Trip Completed");
            getActivity().onBackPressed();
            /*Todo:: end Trip*/
        });

        return root;
    }
    
    public void setTrip(Trip trip) { mTrip = trip; }
    public void setKind(String kind) { mKind = kind; }

    private void showMessage(String message) {
        Toast.makeText(getContext(),""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openStudents(int roadId) {
        Intent intent = new Intent(getContext(), TravelAttendanceActivity.class);
        intent.putExtra("KIND", mKind);
        intent.putExtra("road_id", roadId);
        startActivity(intent);
    }
}