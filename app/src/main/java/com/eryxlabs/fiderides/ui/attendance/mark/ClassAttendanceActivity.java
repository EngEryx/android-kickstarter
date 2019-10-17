package com.eryxlabs.fiderides.ui.attendance.mark;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Stream;

public class ClassAttendanceActivity extends AppCompatActivity {

    private Stream myStream = null;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        loadAttendanceShifts();

        if (getIntent() != null){
            myStream = (Stream) getIntent().getSerializableExtra("STREAM");
            showMessage(myStream.getClassStream());
        }
    }

    private void loadAttendanceShifts() {

    }

    private void showMessage(String message) {
        Toast.makeText(this,""+message, Toast.LENGTH_SHORT).show();
    }
}
