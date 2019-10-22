package com.eryxlabs.fiderides.ui.attendance.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.StudentAttendance;

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
        viewHolder.mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
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
            notifyAsync(i);
        });
        if (studentsList.get(i).getStatus() == 2){
            viewHolder.mReason.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mReason.setVisibility(View.INVISIBLE);
        }
        viewHolder.mReason.setText(studentsList.get(i).getReason());
        viewHolder.mReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                studentsList.get(i).setReason(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void notifyAsync(int p){
        new Handler().post(() -> notifyItemChanged(p));
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDetails;
        public RadioGroup mRadioGroup;
        public AppCompatEditText mReason;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetails = itemView.findViewById(R.id.tv_student_details);
            mRadioGroup = itemView.findViewById(R.id.rg_attendance);
            mReason = itemView.findViewById(R.id.et_reason);
        }
    }

    public void setData(List<StudentAttendance> studentAttendances) {
        studentsList.clear();
        studentsList.addAll(studentAttendances);
        notifyDataSetChanged();
    }

    public List<StudentAttendance> getStudentsList() {
        return studentsList;
    }
}
