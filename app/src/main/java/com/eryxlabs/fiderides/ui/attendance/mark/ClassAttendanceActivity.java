package com.eryxlabs.fiderides.ui.attendance.mark;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Shift;
import com.eryxlabs.fiderides.models.Stream;
import com.eryxlabs.fiderides.models.StudentAttendance;
import com.eryxlabs.fiderides.ui.attendance.adapters.StreamStudentsRecyclerViewAdapter;
import com.eryxlabs.fiderides.ui.attendance.adapters.StreamsRecyclerViewAdapter;
import com.eryxlabs.fiderides.utils.ApiClient;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassAttendanceActivity extends AppCompatActivity {

    private Stream myStream = null;
    private List<Shift> mShifts = null;
    private static List<StudentAttendance> studentAttendanceList = new ArrayList<>();
    private Spinner mSpinner;
    private ProgressDialog progressDialog;
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private StreamStudentsRecyclerViewAdapter mStreamStudentsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_attendance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        progressDialog =  new ProgressDialog(this);
        progressDialog.setCancelable(false);

        mSpinner = findViewById(R.id.spin_shifts);
        loadAttendanceShifts();

        if (getIntent() != null){
            myStream = (Stream) getIntent().getSerializableExtra("STREAM");
        }

        mStreamStudentsRecyclerViewAdapter =  new StreamStudentsRecyclerViewAdapter(this);

        recyclerView =  findViewById(R.id.streams_students_recycler_view);
        emptyView = findViewById(R.id.streams_students_empty_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(mStreamStudentsRecyclerViewAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    Shift option = mShifts.get(position-1);
                    loadStudentAttendance(option.getId(), myStream.getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                showMessage("Select an option");
            }
        });

        FloatingActionButton fab = findViewById(R.id.btn_mark_attendance);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentAttendanceList.clear();
                studentAttendanceList.addAll(mStreamStudentsRecyclerViewAdapter.getStudentsList());
                sendAttendance();
            }
        });
    }

    private void loadStudentAttendance(int attendance_shift_id, int stream_id) {
        progressDialog.setMessage("Getting students ...");
        progressDialog.show();
        ApiClient.with(this)
                .getApiService()
                .getStudentsAttendance(stream_id, attendance_shift_id)
                .enqueue(new Callback<List<StudentAttendance>>() {
                    @Override
                    public void onResponse(Call<List<StudentAttendance>> call, Response<List<StudentAttendance>> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            showStudentAttendance(response.body());
                        }else{
                            //todo show refresh
                        }
                    }

                    @Override
                    public void onFailure(Call<List<StudentAttendance>> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void sendAttendance(){
        progressDialog.setMessage("Getting students ...");
        progressDialog.show();
        ApiClient.with(this)
                .getApiService()
                .saveAttendance(studentAttendanceList)
                .enqueue(new Callback<Object>() {

                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            showMessage("Attendance Saved");
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void showStudentAttendance(List<StudentAttendance> studentAttendances) {
        studentAttendanceList.clear();
        studentAttendanceList.addAll(studentAttendances);
        mStreamStudentsRecyclerViewAdapter.setData(studentAttendances);
    }

    private void loadAttendanceShifts() {
        progressDialog.setMessage("Loading attendance shifts ...");
        progressDialog.show();
        ApiClient.with(this)
                .getApiService()
                .getShifts()
                .enqueue(new Callback<List<Shift>>() {
                    @Override
                    public void onResponse(Call<List<Shift>> call, Response<List<Shift>> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            populateShifts(response.body());
                        }else{
                            //todo show refresh
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Shift>> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void populateShifts(List<Shift> shifts) {
        mShifts = shifts;
        List<String> options = new ArrayList<>();
        options.add(" --  Select Attendance Shift --");
        for(Shift shift : mShifts)
            options.add(shift.getDescription());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.spinner_ride_row,
                options
        );
        mSpinner.setAdapter(adapter);
    }

    private void showMessage(String message) {
        Toast.makeText(this,""+message, Toast.LENGTH_SHORT).show();
    }
}
