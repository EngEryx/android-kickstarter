package com.eryxlabs.fiderides.ui.assessment;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.ui.assessment.adapters.StudentIndividualAdapter;

import java.io.File;
import java.util.ArrayList;

public class StudentsIndividualActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentIndividualAdapter studentIndividualAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_individual);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        studentIndividualAdapter = new StudentIndividualAdapter(getApplicationContext());
        recyclerView.setAdapter(studentIndividualAdapter);

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
