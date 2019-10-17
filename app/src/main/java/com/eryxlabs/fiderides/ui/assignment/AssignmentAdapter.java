package com.eryxlabs.fiderides.ui.assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.eryxlabs.fiderides.models.Assignment;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>  {

    List<Assignment> assignments=new ArrayList<>();
    Context context;
    public AssignmentAdapter(Context c){
        this.context=c;

    }


    public void updateData(List<Assignment> updateList){
        this.assignments=updateList;
        this.notifyDataSetChanged();


    }


    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder assignmentViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder{


        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
