package com.eryxlabs.fiderides.ui.attendance;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Stream;
import com.eryxlabs.fiderides.ui.attendance.adapters.StreamsRecyclerViewAdapter;
import com.eryxlabs.fiderides.utils.ApiClient;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceFragment extends Fragment {

    private AttendanceViewModel attendanceViewModel;
    private List<Stream> streamsList =  new ArrayList<>();
    private ProgressDialog progressDialog;
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private StreamsRecyclerViewAdapter mStreamsRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_attendance, container, false);
        progressDialog =  new ProgressDialog(getContext());
        progressDialog.setCancelable(false);

        recyclerView =  root.findViewById(R.id.streams_recycler_view);
        emptyView = root.findViewById(R.id.streams_empty_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(mStreamsRecyclerViewAdapter);

        loadStreams();
        
        return root;
    }

    private void loadStreams() {
        progressDialog.setMessage("Loading rides ...");
        progressDialog.show();
        ApiClient.with(getContext())
                .getApiService()
                .getStreams()
                .enqueue(new Callback<List<Stream>>() {
                    @Override
                    public void onResponse(Call<List<Stream>> call, Response<List<Stream>> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            showStreams(response.body());
                        }else{
                            //todo show refresh
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Stream>> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void showStreams(List<Stream> streams) {
        streamsList.clear();
        streamsList.addAll(streams);
        mStreamsRecyclerViewAdapter.setData(streamsList);
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(),""+message, Toast.LENGTH_SHORT).show();
    }
}