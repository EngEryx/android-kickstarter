package com.eryxlabs.fiderides.ui.travel.mark;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.ui.travel.TravelViewModel;
import com.eryxlabs.fiderides.ui.travel.adapters.TravelStudentsAdapter;

public class TravelAttendanceActivity extends AppCompatActivity {

    private RecyclerView list;
    private TravelStudentsAdapter travelStudentsAdapter;
    private int road_id;
    private String kind;
    private TravelViewModel travelViewModel;
    private ProgressBar pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_attendance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        pr = findViewById(R.id.pr);

        road_id = getIntent().getIntExtra("road_id",0);
        kind = getIntent().getStringExtra("KIND");

        list = findViewById(R.id.streams_students_recycler_view);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        travelStudentsAdapter = new TravelStudentsAdapter(kind);
        list.setAdapter(travelStudentsAdapter);

        travelViewModel = ViewModelProviders.of(this).get(TravelViewModel.class);
        travelViewModel.loadTravelStudents(road_id);

        travelViewModel.records.observe(this,travelRecords -> {
            travelStudentsAdapter.updateData(travelRecords);
        });

        travelViewModel.monitor.observe(this,networkResponse -> {
            if (networkResponse!=null){
                if (networkResponse.is_loading){
                    pr.setVisibility(View.VISIBLE);
                }else{
                    pr.setVisibility(View.GONE);
                }

                if (networkResponse.message!=null){
                    if (networkResponse.message.equals("Updated")){
                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }else{
                        Snackbar.make(pr,networkResponse.message,Snackbar.LENGTH_SHORT).show();
                    }

                }
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v->{
            travelViewModel.updateTravelRecords(travelStudentsAdapter.getRecords());
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
