package com.eryxlabs.fiderides.ui.attendance.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Student;
import com.eryxlabs.fiderides.models.StudentAttendance;
import com.eryxlabs.fiderides.ui.attendance.mark.ClassAttendanceActivity;

import java.util.ArrayList;
import java.util.List;

public class StreamStudentsRecyclerViewAdapter extends RecyclerView.Adapter<StreamStudentsRecyclerViewAdapter.ViewHolder> {
    private List<StudentAttendance> studentsList;
    public Context context;

    public StreamStudentsRecyclerViewAdapter(Context ctx){
        this.studentsList = new ArrayList<>();
        this.context = ctx;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_attendance_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final StudentAttendance studentAttendance = studentsList.get(i);
        viewHolder.tvDetails.setText(studentAttendance.getStudent().getFullName());
        switch (studentAttendance.getStatus()){
            case 1:
                viewHolder.mRadioGroup.check(R.id.rb_present);
                break;
            case 2:
                viewHolder.mRadioGroup.check(R.id.rb_half_present);
                break;
            default:
                viewHolder.mRadioGroup.check(R.id.rb_absent);
                break;
        }
        viewHolder.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int status;
                switch (checkedId) {
                    case R.id.rb_present:
                        status = 1;
                        break;
                    case R.id.rb_half_present:
                        status = 2;
                        break;
                    default:
                        status = 3;
                        break;
                }
                studentsList.get(i).setStatus(status);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDetails;
        public RadioGroup mRadioGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetails = itemView.findViewById(R.id.tv_student_details);
            mRadioGroup = itemView.findViewById(R.id.rg_attendance);
        }
    }

    public void setData(List<StudentAttendance> studentAttendances) {
        studentsList.clear();
        studentsList.addAll(studentAttendances);
        notifyDataSetChanged();
    }
}
