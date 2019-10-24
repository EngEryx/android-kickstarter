package com.eryxlabs.fiderides.ui.travel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripsRecyclerViewAdapter extends RecyclerView.Adapter<TripsRecyclerViewAdapter.ViewHolder> {
    private List<Trip> tripsList;
    public Context context;
    private TripsRecyclerViewAdapterInterface tripsRecyclerViewAdapterInterface;

    public TripsRecyclerViewAdapter(Context ctx, TripsRecyclerViewAdapterInterface tripsRecyclerViewAdapterInterface){
        this.tripsList = new ArrayList<>();
        this.tripsRecyclerViewAdapterInterface=tripsRecyclerViewAdapterInterface;
        this.context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_item, viewGroup,false);
        return new TripsRecyclerViewAdapter.ViewHolder(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Trip trip = tripsList.get(i);
        viewHolder.tvTrip.setText(trip.getName());
        viewHolder.tvStudentsCount.setText(String.format("Students : %s", trip.getStudentsCount()));
        viewHolder.ivMore.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, viewHolder.ivMore);
            popupMenu.inflate(R.menu.travel_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.evening_mark:
                        tripsRecyclerViewAdapterInterface.openRoutes("from", tripsList.get(i));
                        return true;
                    case R.id.morning_mark:
                        tripsRecyclerViewAdapterInterface.openRoutes("to", tripsList.get(i));
                        return true;
                    default:
                        return false;
                }
            });
            @SuppressLint("RestrictedApi")
            MenuPopupHelper menuHelper = new MenuPopupHelper(context, (MenuBuilder) popupMenu.getMenu(),viewHolder.ivMore);
            menuHelper.setForceShowIcon(true);
            menuHelper.show();
        });
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }

    public void setData(List<Trip> trips) {
        tripsList.clear();
        tripsList.addAll(trips);
        notifyDataSetChanged();
    }

    public interface TripsRecyclerViewAdapterInterface{
        void openRoutes(String kind, Trip trip);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvTrip;
        public AppCompatTextView tvStudentsCount;
        public ImageView ivMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrip = itemView.findViewById(R.id.tv_trip);
            tvStudentsCount = itemView.findViewById(R.id.tv_students_count);
            ivMore = itemView.findViewById(R.id.iv_more_details);
        }
    }


}
