package com.eryxlabs.fiderides.ui.travel.trip;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.Trip;
import com.eryxlabs.fiderides.ui.travel.TripFragment;

public class TripActivity extends AppCompatActivity {

    private Trip mTrip;
    private String mKind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null){
            mTrip = (Trip) getIntent().getSerializableExtra("TRIP");
            mKind = getIntent().getStringExtra("KIND");
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("TRIP", mTrip);
        bundle.putString("KIND", mKind);

        FragmentManager fragmentManager = getSupportFragmentManager();
        TripFragment tripFragment = new TripFragment();
        tripFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.frag_routes, tripFragment)
                .commit();



        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void showMessage(String message) {
        Toast.makeText(this,"" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
