package com.eryxlabs.fiderides.ui.attendance;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Stream;
import com.eryxlabs.fiderides.ui.attendance.adapters.StreamsRecyclerViewAdapter;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceFragment extends Fragment {

    private AttendanceViewModel attendanceViewModel;
    private TextView textView;
    private ProgressDialog progressDialog;
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private StreamsRecyclerViewAdapter mStreamsRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_attendance, container, false);
        textView = root.findViewById(R.id.text_attendance);

        attendanceViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}