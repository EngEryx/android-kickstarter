package com.eryxlabs.fiderides;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eryxlabs.fiderides.models.Ride;
import com.eryxlabs.fiderides.ui.LoginActivity;
import com.eryxlabs.fiderides.ui.adapters.RidesRecyclerViewAdapter;
import com.eryxlabs.fiderides.utils.ApiClient;
import com.eryxlabs.fiderides.utils.Cache;
import com.eryxlabs.fiderides.utils.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private List<Ride> ridesList =  new ArrayList<>();
    private EmptyRecyclerView recyclerView;
    private LinearLayout emptyView;
    private RidesRecyclerViewAdapter mRidesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRidesRecyclerViewAdapter =  new RidesRecyclerViewAdapter(this);

        if(!Cache.hasAuthToken(this)){
            logout();
            return;
        }

        progressDialog =  new ProgressDialog(this);
        progressDialog.setCancelable(false);

        recyclerView =  findViewById(R.id.rides_recycler_view);
        emptyView = findViewById(R.id.empty_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(mRidesRecyclerViewAdapter);

        loadRides();
    }

    private void loadRides() {
        progressDialog.setMessage("Loading rides ...");
        progressDialog.show();
        ApiClient.with(this)
                .getApiService()
                .getRides()
                .enqueue(new Callback<List<Ride>>() {
                    @Override
                    public void onResponse(Call<List<Ride>> call, Response<List<Ride>> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            showRides(response.body());
                        }else{
                            //todo show refresh
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ride>> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void showRides(List<Ride> rides) {
        ridesList.clear();
        ridesList.addAll(rides);
        mRidesRecyclerViewAdapter.setData(ridesList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId() == R.id.action_logout){
           Cache.removeAuthToken(this);
           logout();
       }else if(item.getItemId() == R.id.action_refresh){
           loadRides();
       }
       return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Cache.removeAuthToken(this);

        Intent i = new Intent(this,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    private void showMessage(String authToken) {
        Toast.makeText(this,""+authToken,Toast.LENGTH_SHORT).show();
    }

}
