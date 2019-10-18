package com.eryxlabs.fiderides.ui.assessment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eryxlabs.fiderides.R;

public class StudentGroupAdapter extends RecyclerView.Adapter<StudentGroupAdapter.GroupStudentHolder> {

    private Context context;
    private StudentGroupAdapterInterface interface_s;

    public StudentGroupAdapter(Context context, StudentGroupAdapterInterface interface_s){

        this.context = context;
        this.interface_s = interface_s;
    }
    @NonNull
    @Override
    public GroupStudentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.individual_student_group_row,viewGroup,false);
        return new GroupStudentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupStudentHolder individuaStudentHolder, int i) {

        individuaStudentHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class GroupStudentHolder extends RecyclerView.ViewHolder{

        public GroupStudentHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface StudentGroupAdapterInterface{
        void mark();
    }
}
