package com.eryxlabs.fiderides.ui.attendance.mark;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Shift;
import com.eryxlabs.fiderides.models.Stream;
import com.eryxlabs.fiderides.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassAttendanceActivity extends AppCompatActivity {

    private Stream myStream = null;
    private Spinner mSpinner;
    private ProgressDialog progressDialog;

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
            showMessage(myStream.getClassStream());
        }
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
        List<String> options = new ArrayList<>();
        options.add(" --  Select Attendance Shift --");
        for(Shift shift : shifts)
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
