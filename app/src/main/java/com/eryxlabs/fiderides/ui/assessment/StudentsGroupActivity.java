package com.eryxlabs.fiderides.ui.assessment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.models.Result;
import com.eryxlabs.fiderides.ui.assessment.adapters.GroupsAdapter;
import com.eryxlabs.fiderides.ui.assessment.dialogs.ManageStudentsGroups;
import com.eryxlabs.fiderides.ui.assessment.dialogs.MarkAssessment;
import com.eryxlabs.fiderides.utils.CoreUtils;

import java.util.List;

public class StudentsGroupActivity extends AppCompatActivity implements GroupsAdapter.GroupsAdapterInterface {

    private RecyclerView recyclerView;
    private GroupsAdapter groupsAdapter;
    private AppCompatTextView manage_students;
    Assessment assessment;
    AssessmentViewModel assessmentViewModel;
    AppCompatTextView subject,date,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_group);

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
        manage_students = findViewById(R.id.manage_students);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        groupsAdapter = new GroupsAdapter(getApplicationContext(),this);
        recyclerView.setAdapter(groupsAdapter);

        manage_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ManageStudentsGroups().show(getSupportFragmentManager(),"Manage students");
            }
        });
        if (assessment!=null){

            date.setText(CoreUtils.dateTimeFormatter(assessment.getDate()));
            description.setText(assessment.getDescription());

            if (assessment.getSubject()!=null){
                subject.setText(assessment.getSubject().getName());
            }
            //get the student names/records
            assessmentViewModel.getStudentRecordsOnline(assessment.getId());

        }else{

            finish();

        }

        assessmentViewModel.assessmentResults.observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                if (results!=null){

                    groupsAdapter.updateData(results);
                }
            }
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

    @Override
    public void mark(Result result) {
        new MarkAssessment().show(getSupportFragmentManager(),"Mark modal");

    }
}
