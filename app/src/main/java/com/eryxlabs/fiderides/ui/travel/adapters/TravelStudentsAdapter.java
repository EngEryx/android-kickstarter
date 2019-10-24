package com.eryxlabs.fiderides.ui.travel.adapters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.TravelRecord;

import java.util.ArrayList;
import java.util.List;

public class TravelStudentsAdapter extends RecyclerView.Adapter<TravelStudentsAdapter.TravelStudentHolder> {

    private List<TravelRecord> travelRecords = new ArrayList<>();
    private String kind;

    public TravelStudentsAdapter(String kind){

        this.kind = kind;
    }

    @NonNull
    @Override
    public TravelStudentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_travel_attendance_item, viewGroup,false);
        return new TravelStudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelStudentHolder travelStudentHolder, int i) {

        TravelRecord current = travelRecords.get(i);
        travelStudentHolder.student.setText(current.getStudent().getFullName());
        travelStudentHolder.collection.setOnCheckedChangeListener((radioGroup, id) -> {
            if(this.kind.equals("to")){
                current.setPickedHome((id == R.id.rb_travel_present) ? 1 : 0);
            }else{
                current.setDroppedHome((id == R.id.rb_travel_present) ? 1 : 0);
            }
            notifyAsync(i);
        });

        if(this.kind.equals("to")){
            travelStudentHolder.collection.check((current.getPickedHome()==0) ? R.id.rb_travel_absent : R.id.rb_travel_present);
        }else{
            travelStudentHolder.present.setText("Dropped");
            travelStudentHolder.absent.setText("En-route");
            travelStudentHolder.collection.check((current.getDroppedHome()==0) ? R.id.rb_travel_absent : R.id.rb_travel_present);
        }
    }

    public void notifyAsync(int p){
        new Handler().post(() -> notifyItemChanged(p));
    }

    @Override
    public int getItemCount() {
        return travelRecords.size();
    }

    public void updateData(List<TravelRecord> travelRecords) {
        this.travelRecords = travelRecords;
        notifyDataSetChanged();
    }

    public List<TravelRecord> getRecords() {
        return travelRecords;
    }

    public class TravelStudentHolder extends RecyclerView.ViewHolder{
        private TextView student;
        private RadioGroup collection;
        public RadioButton absent;
        public RadioButton present;
        public TravelStudentHolder(@NonNull View itemView) {
            super(itemView);
            student = itemView.findViewById(R.id.tv_student_details);
            collection = itemView.findViewById(R.id.rg_attendance);
            absent = itemView.findViewById(R.id.rb_travel_absent);
            present = itemView.findViewById(R.id.rb_travel_present);
        }
    }
}
