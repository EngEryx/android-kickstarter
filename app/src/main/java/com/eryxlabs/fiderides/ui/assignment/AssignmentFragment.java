package com.eryxlabs.fiderides.ui.assignment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.ui.assignment.AssignmentViewModel;

public class AssignmentFragment extends Fragment {

    private AssignmentViewModel assignmentViewModel;
    RecyclerView recyclerView;
        AssignmentAdapter assignmentAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        assignmentViewModel =
                ViewModelProviders.of(this).get(AssignmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        final TextView textView = root.findViewById(R.id.text_assignment);

        recyclerView=root.findViewById(R.id.assignment_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        assignmentAdapter=new AssignmentAdapter(getActivity());
        recyclerView.setAdapter(assignmentAdapter);


        assignmentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}