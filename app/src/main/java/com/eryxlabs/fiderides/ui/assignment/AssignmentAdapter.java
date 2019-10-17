package com.eryxlabs.fiderides.ui.assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eryxlabs.fiderides.R;
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
        View item_view = LayoutInflater.from(context).inflate(R.layout.single_assignment_adapter,viewGroup,false);
        return new AssignmentViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int i) {

        Assignment assignment=assignments.get(i);
        holder.dueDate.setText(assignment.getDueDate());
        holder.description.setText(assignment.getDescription());

    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder{
        public AppCompatTextView dueDate;
        public AppCompatTextView description;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);

            dueDate = itemView.findViewById(R.id.due_date);
            description=itemView.findViewById(R.id.description);

        }
    }
}
