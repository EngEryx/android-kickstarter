package com.eryxlabs.fiderides.ui.assessment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eryxlabs.fiderides.R;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentHolder> {

    private Context context;
    private AssesmentAdapterInterface assesmentAdapterInterface;

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
                                assesmentAdapterInterface.openStudents();
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
                @SuppressLint("RestrictedApi") MenuPopupHelper menuHelper = new MenuPopupHelper(context,(MenuBuilder)popup.getMenu(),assessmentHolder.more);
                menuHelper.setForceShowIcon(true);
                menuHelper.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class AssessmentHolder extends RecyclerView.ViewHolder{
        public ImageView more;
        public AssessmentHolder(@NonNull View itemView) {
            super(itemView);
            more = itemView.findViewById(R.id.more_menu);
        }
    }


    public interface AssesmentAdapterInterface{
        void openStudents();
    }
}
