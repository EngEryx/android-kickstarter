package com.eryxlabs.fiderides.ui.assessment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.models.Result;
import com.eryxlabs.fiderides.ui.assessment.adapters.StudentIndividualAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StudentsIndividualActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentIndividualAdapter studentIndividualAdapter;
    AppCompatTextView subject,date,description;
    Assessment assessment;
    AssessmentViewModel assessmentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_individual);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//get the assesment object
        if (getIntent() != null){
            assessment = (Assessment) getIntent().getSerializableExtra("assessment");
        }
        assessmentViewModel= ViewModelProviders.of(this).get(AssessmentViewModel.class);
        subject=findViewById(R.id.subject);
        date=findViewById(R.id.date);
        description=findViewById(R.id.description);
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        studentIndividualAdapter = new StudentIndividualAdapter(getApplicationContext());
        recyclerView.setAdapter(studentIndividualAdapter);


        if (assessment!=null){

                subject.setText(assessment.getSubjectId()+"");
                date.setText(assessment.getDate());
                description.setText(assessment.getDescription());


                //get the student names/records
            getStudentRecordsOnline(assessment.getId());
        }else{

            finish();
        }



        assessmentViewModel.assessmentResults.observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                if (results!=null){

                    studentIndividualAdapter.updateData(results);
                }
            }
        });
    }

    private void getStudentRecordsOnline(int id) {

        assessmentViewModel.getStudentRecordsOnline(id);
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
