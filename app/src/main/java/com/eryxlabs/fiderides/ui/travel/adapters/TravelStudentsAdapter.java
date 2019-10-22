package com.eryxlabs.fiderides.ui.travel.adapters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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
        travelStudentHolder.absent.setOnClickListener(v->{
            if(this.kind.equals("to")){
                current.setPicked(0);
            }else{
                current.setDropped(0);
            }
            notifyAsync(i);

        });

        travelStudentHolder.present.setOnClickListener(v->{
            if(this.kind.equals("to")){
                current.setPicked(1);
            }else{
                current.setDropped(1);
            }

            notifyAsync(i);
        });

        if(this.kind.equals("to")){
            if(current.getPicked()==0){
                travelStudentHolder.present.setChecked(false);
                travelStudentHolder.absent.setChecked(true);
            }else{
                travelStudentHolder.present.setChecked(true);
                travelStudentHolder.absent.setChecked(false);
            }
        }else{
            travelStudentHolder.present.setText("Dropped");
            travelStudentHolder.absent.setText("En-route");
            if(current.getDropped()==0){
                travelStudentHolder.present.setChecked(false);
                travelStudentHolder.absent.setChecked(true);
            }else{
                travelStudentHolder.present.setChecked(true);
                travelStudentHolder.absent.setChecked(false);
            }
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
        public RadioButton absent;
        public RadioButton present;
        public TravelStudentHolder(@NonNull View itemView) {
            super(itemView);

            student = itemView.findViewById(R.id.tv_student_details);
            absent = itemView.findViewById(R.id.rb_travel_absent);
            present = itemView.findViewById(R.id.rb_travel_present);
        }
    }
}
