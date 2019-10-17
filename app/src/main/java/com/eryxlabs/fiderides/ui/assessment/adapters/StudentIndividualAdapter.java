package com.eryxlabs.fiderides.ui.assessment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eryxlabs.fiderides.R;

public class StudentIndividualAdapter extends RecyclerView.Adapter<StudentIndividualAdapter.IndividuaStudentHolder> {

    private Context context;

    public StudentIndividualAdapter(Context context){

        this.context = context;
    }
    @NonNull
    @Override
    public IndividuaStudentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.individual_student_row,viewGroup,false);
        return new IndividuaStudentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IndividuaStudentHolder individuaStudentHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class IndividuaStudentHolder extends RecyclerView.ViewHolder{

        public IndividuaStudentHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
