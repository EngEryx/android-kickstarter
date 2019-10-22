package com.eryxlabs.fiderides.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Ride;
import com.eryxlabs.fiderides.ui.BookActivity;

import java.util.ArrayList;
import java.util.List;

public class RidesRecyclerViewAdapter extends RecyclerView.Adapter<RidesRecyclerViewAdapter.ViewHolder> {
    private List<Ride> rideList;
    public Context context;

    public RidesRecyclerViewAdapter(Context ctx){
        this.rideList = new ArrayList<>();
        this.context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rides_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int i) {
        final Ride ride = rideList.get(i);
        vh.tvDetails.setText(ride.getDetails());
        vh.tvPassengers.setText(String.format("Passengers : %s",ride.getPassengers()));
        vh.tvRemain.setText(String.format("Remaining Seats : %s",ride.getRemaining_seats()));
        vh.tvDriver.setText(String.format("Ride with %s on #:",ride.getUser().getFirstName()+" "+ride.getUser().getLastName()));
        vh.tvDayTime.setText(ride.getDuration());
        vh.btnParcel.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra("MYRIDE",ride);
            intent.putExtra("TYPE","parcel");
            context.startActivity(intent);
        });

        vh.btnBook.setOnClickListener( v -> {
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra("MYRIDE",ride);
            intent.putExtra("TYPE","book");
            context.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return rideList.size();
    }

    public void setData(List<Ride> rides) {
        rideList.clear();
        rideList.addAll(rides);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDetails;
        public TextView tvDayTime;
        public TextView tvPassengers;
        public TextView tvRemain;
        public TextView tvDriver;
        public Button btnBook;
        public Button btnParcel;
        ViewHolder(@NonNull View v) {
            super(v);
            tvDayTime = v.findViewById(R.id.tvDayTime);
            tvPassengers = v.findViewById(R.id.tvPassengers);
            tvRemain = v.findViewById(R.id.tvRemain);
            tvDetails = v.findViewById(R.id.tvDetails);
            tvDriver = v.findViewById(R.id.tvDriver);
            btnBook = v.findViewById(R.id.btnBook);
            btnParcel = v.findViewById(R.id.btnParcel);
        }
    }
}
