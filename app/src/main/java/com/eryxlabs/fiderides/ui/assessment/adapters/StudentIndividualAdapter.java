package com.eryxlabs.fiderides.ui.assessment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Result;
import com.eryxlabs.fiderides.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentIndividualAdapter extends RecyclerView.Adapter<StudentIndividualAdapter.IndividuaStudentHolder> {

    private Context context;
    private List<Result> resultList=new ArrayList<>();
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
    public void onBindViewHolder(@NonNull IndividuaStudentHolder holder, int i) {
            Result result=resultList.get(i);


            if (result.getStudent()!=null){
                Student student=result.getStudent();
                holder.studentName.setText(student.getFullName() +" ("+ student.reg_no+ ")" );

            }

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public void updateData(List<Result> updatedData){

        this.resultList=updatedData;
        this.notifyDataSetChanged();
    }
    public class IndividuaStudentHolder extends RecyclerView.ViewHolder{
        AppCompatTextView studentName;
        public IndividuaStudentHolder(@NonNull View itemView) {
            super(itemView);

            studentName=itemView.findViewById(R.id.student_name);
        }
    }


}
