package com.eryxlabs.fiderides.ui.attendance.mark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eryxlabs.fiderides.R;

public class MarkAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
