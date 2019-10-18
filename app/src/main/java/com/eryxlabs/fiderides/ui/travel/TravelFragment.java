package com.eryxlabs.fiderides.ui.travel;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eryxlabs.fiderides.MainActivity;
import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Route;
import com.eryxlabs.fiderides.models.Stream;
import com.eryxlabs.fiderides.ui.attendance.adapters.StreamsRecyclerViewAdapter;
import com.eryxlabs.fiderides.ui.travel.adapters.RoutesRecyclerViewAdapter;
import com.eryxlabs.fiderides.ui.travel.mark.TravelAttendanceActivity;
import com.eryxlabs.fiderides.utils.ApiClient;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelFragment extends Fragment implements RoutesRecyclerViewAdapter.RoutesRecyclerViewAdapterInterface {

    private TravelViewModel travelViewModel;
    private List<Route> routesList =  new ArrayList<>();
    private ProgressDialog progressDialog;
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private RoutesRecyclerViewAdapter mRoutesRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        travelViewModel = ViewModelProviders.of(this).get(TravelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_travel, container, false);
        mRoutesRecyclerViewAdapter =  new RoutesRecyclerViewAdapter(getContext(),this);

        progressDialog =  new ProgressDialog(getContext());
        progressDialog.setCancelable(false);

        recyclerView =  root.findViewById(R.id.travel_recycler_view);
        emptyView = root.findViewById(R.id.travel_empty_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(mRoutesRecyclerViewAdapter);

        loadRoutes();

        return root;
    }

    private void loadRoutes() {
        progressDialog.setMessage("Loading routes ...");
        progressDialog.show();
        ApiClient.with(getContext())
                .getApiService()
                .getRoutes()
                .enqueue(new Callback<List<Route>>() {
                    @Override
                    public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            showRoutes(response.body());
                        }else{
                            //todo show refresh
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Route>> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void showRoutes(List<Route> routes) {
        routesList.clear();
        routesList.addAll(routes);
        mRoutesRecyclerViewAdapter.setData(routesList);
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(),""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openStudents(String kind, int roadId) {
        Intent intent = new Intent(getContext(), TravelAttendanceActivity.class);
        intent.putExtra("KIND", kind);
        intent.putExtra("road_id", roadId);
        startActivity(intent);
    }
}