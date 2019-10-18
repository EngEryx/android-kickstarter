package com.eryxlabs.fiderides.ui.assessment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Assessment;
import com.eryxlabs.fiderides.utils.CoreUtils;

import java.util.ArrayList;
import java.util.List;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentHolder> {

    private Context context;
    private AssesmentAdapterInterface assesmentAdapterInterface;
    private List<Assessment> assessmentList=new ArrayList<>();
    public AssessmentsAdapter(Context context,AssesmentAdapterInterface assesmentAdapterInterface){

        this.context = context;
        this.assesmentAdapterInterface = assesmentAdapterInterface;
    }

    @NonNull
    @Override
    public AssessmentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_assessment_row,viewGroup,false);
        return new AssessmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentHolder assessmentHolder, int i) {

        Assessment assessment=assessmentList.get(i);
        assessmentHolder.more.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, assessmentHolder.more);
                //inflating menu from xml resource
                popup.inflate(R.menu.assessments_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ic_action_view:
                                return true;
                            case R.id.ic_action_results:
                                return true;
                            case R.id.ic_action_students:
                                assesmentAdapterInterface.openStudents(assessment);
                                return true;
                            case R.id.ic_action_edit:
                                return true;
                            case R.id.ic_action_delete:
                                return true;
                            default:
                                return false;
                        }

                    }
                });

                @SuppressLint("RestrictedApi")
                MenuPopupHelper menuHelper = new MenuPopupHelper(context,(MenuBuilder)popup.getMenu(),assessmentHolder.more);
                menuHelper.setForceShowIcon(true);
                menuHelper.show();
            }
        });


        assessmentHolder.subject.setText(assessment.getDescription());
        assessmentHolder.dueDate.setText(CoreUtils.dateTimeFormatter(assessment.getDate()));

    }

    @Override
    public int getItemCount() {
        return assessmentList.size();
    }

    public class AssessmentHolder extends RecyclerView.ViewHolder{
        public ImageView more;
        public AppCompatTextView dueDate;
        public AppCompatTextView subject;
        public AssessmentHolder(@NonNull View itemView) {
            super(itemView);
            more = itemView.findViewById(R.id.more_menu);
            dueDate=itemView.findViewById(R.id.due_date);
            subject=itemView.findViewById(R.id.subject);
        }
    }


    public interface AssesmentAdapterInterface{
        void openStudents(Assessment assessment);
    }

    public void updateData(List<Assessment> updatedList){

        this.assessmentList=updatedList;
        this.notifyDataSetChanged();
    }
}
