package com.eryxlabs.fiderides.ui.attendance.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Stream;

import java.util.ArrayList;
import java.util.List;

public class ClassesRecyclerViewAdapter extends RecyclerView.Adapter<ClassesRecyclerViewAdapter.ViewHolder> {
    private List<Stream> classesList;
    public Context context;

    public ClassesRecyclerViewAdapter(Context ctx){
        this.classesList = new ArrayList<>();
        this.context = ctx;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.classes_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Stream classLevel = classesList.get(i);
        viewHolder.tvDetails.setText(classLevel.getId());
        viewHolder.tvStudents.setText(String.format("Students : %s",classLevel.getId()));
//        viewHolder.tvTeacher.setText(String.format("Class moderator :", classLevel.getUser().getFirstName()+" "+ ride.getUser().getLastName()));
        viewHolder.markAttendance.setOnClickListener(v -> {
            Toast.makeText(context, "Todo: Mark Attendance", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return classesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDetails;
        public TextView tvStudents;
        public TextView tvTeacher;
        public Button markAttendance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetails = itemView.findViewById(R.id.tv_class_details);
            tvStudents = itemView.findViewById(R.id.tv_class_students_count);
            tvTeacher = itemView.findViewById(R.id.tv_class_teacher);
            markAttendance = itemView.findViewById(R.id.btn_mark_attendance);
        }
    }

    /*public void setData(List<Ride> rides) {
        rideList.clear();
        rideList.addAll(rides);
        notifyDataSetChanged();
    }*/
}
