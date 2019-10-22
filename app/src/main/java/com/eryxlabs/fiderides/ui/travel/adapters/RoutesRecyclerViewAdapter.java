package com.eryxlabs.fiderides.ui.travel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.PopupMenu;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Route;

import java.util.ArrayList;
import java.util.List;

public class RoutesRecyclerViewAdapter extends RecyclerView.Adapter<RoutesRecyclerViewAdapter.ViewHolder> {
    private List<Route> routesList;
    public Context context;
    private RoutesRecyclerViewAdapterInterface routesRecyclerViewAdapterInterface;

    public RoutesRecyclerViewAdapter(Context ctx,RoutesRecyclerViewAdapterInterface routesRecyclerViewAdapterInterface){
        this.routesList = new ArrayList<>();
        this.routesRecyclerViewAdapterInterface=routesRecyclerViewAdapterInterface;
        this.context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.route_item, viewGroup,false);
        return new RoutesRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Route route = routesList.get(i);
        viewHolder.tvRoute.setText(route.getName());
        viewHolder.tvStudentsCount.setText(String.format("Students : %s", route.getStudentsCount()));
        viewHolder.ivMore.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, viewHolder.ivMore);
            popupMenu.inflate(R.menu.travel_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.evening_mark:
                        routesRecyclerViewAdapterInterface.openStudents("from", routesList.get(i).getId());
                        return true;
                    case R.id.morning_mark:
                        routesRecyclerViewAdapterInterface.openStudents("to", routesList.get(i).getId());
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
        return routesList.size();
    }

    public void setData(List<Route> routes) {
        routesList.clear();
        routesList.addAll(routes);
        notifyDataSetChanged();
    }

    public interface RoutesRecyclerViewAdapterInterface{
        void openStudents(String kind, int roadId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvRoute;
        public AppCompatTextView tvStudentsCount;
        public ImageView ivMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoute = itemView.findViewById(R.id.tv_route);
            tvStudentsCount = itemView.findViewById(R.id.tv_students_count);
            ivMore = itemView.findViewById(R.id.iv_more_details);
        }
    }


}
