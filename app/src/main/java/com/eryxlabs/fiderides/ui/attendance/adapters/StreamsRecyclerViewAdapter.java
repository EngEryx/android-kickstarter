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

public class StreamsRecyclerViewAdapter extends RecyclerView.Adapter<StreamsRecyclerViewAdapter.ViewHolder> {
    private List<Stream> streamsList;
    public Context context;

    public StreamsRecyclerViewAdapter(Context ctx){
        this.streamsList = new ArrayList<>();
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
        final Stream stream = streamsList.get(i);
        viewHolder.tvDetails.setText(stream.getClassStream());
        viewHolder.tvStudents.setText(String.format("Students : %s", stream.getStudentsCount()));
        viewHolder.tvTeacher.setText(String.format("Class moderator : %s", stream.getTeacher().getFullName()));
        viewHolder.markAttendance.setOnClickListener(v -> {
            Toast.makeText(context, "Todo: Mark Attendance", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return streamsList.size();
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

    public void setData(List<Stream> streams) {
        streamsList.clear();
        streamsList.addAll(streams);
        notifyDataSetChanged();
    }
}
