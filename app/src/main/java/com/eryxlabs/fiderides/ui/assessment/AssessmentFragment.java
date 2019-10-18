package com.eryxlabs.fiderides.ui.assessment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.ui.assessment.adapters.AssessmentsAdapter;
import com.eryxlabs.fiderides.ui.assessment.dialogs.AddAssessment;

import java.util.List;

public class AssessmentFragment extends Fragment implements AssessmentsAdapter.AssesmentAdapterInterface {

    private AssessmentsAdapter assessmentsAdapter;
    private RecyclerView recyclerView;
    private AppCompatButton add_button;


    AssessmentViewModel assessmentViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_assessment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assessmentViewModel= ViewModelProviders.of(this).get(AssessmentViewModel.class);

        assessmentViewModel.getAllAssessmentsOnline();
        recyclerView = view.findViewById(R.id.list);
        add_button = view.findViewById(R.id.add_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        assessmentsAdapter = new AssessmentsAdapter(getActivity(),this);
        recyclerView.setAdapter(assessmentsAdapter);


        add_button.setOnClickListener(v -> {
            new AddAssessment().show(getChildFragmentManager(),"Add assessment");
        });

        assessmentViewModel.assessments.observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(@Nullable List<Assessment> assessments) {
                if (assessments!=null){
                    assessmentsAdapter.updateData(assessments);
                }
            }
        });

    }

    @Override
    public void openStudents(Assessment assessment) {
        //0-individual 1-group

        Intent t;
        if (assessment.getType()==0){
            t = new Intent(getActivity(),StudentsIndividualActivity.class);
        }else{
             t = new Intent(getActivity(),StudentsGroupActivity.class);
        }
        t.putExtra("assessment", assessment);
        getActivity().startActivity(t);
    }
}
