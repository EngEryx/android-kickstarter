package com.eryxlabs.fiderides.ui.assessment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eryxlabs.fiderides.R;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupHolder> {
    private Context context;
    private GroupsAdapterInterface groupsAdapterInterface;

    public GroupsAdapter(Context context,GroupsAdapterInterface groupsAdapterInterface){

        this.context = context;
        this.groupsAdapterInterface = groupsAdapterInterface;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.assessment_group_row,viewGroup,false);
        return new GroupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder groupHolder, int i) {

        groupHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupsAdapterInterface.mark();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class GroupHolder extends RecyclerView.ViewHolder{

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface GroupsAdapterInterface{
        void mark();
    }
}
