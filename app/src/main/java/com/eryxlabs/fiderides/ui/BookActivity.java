package com.eryxlabs.fiderides.ui;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.BookStatus;
import com.eryxlabs.fiderides.models.Destination;
import com.eryxlabs.fiderides.models.Ride;
import com.eryxlabs.fiderides.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {
    private Ride myRide = null;
    private Spinner spinner;
    private Button btnProceed;
    private Button btnVerifyPay;
    private String booktype = null;
    private ProgressDialog progressDialog;
    private Destination destination = null;
    private CardView bookCard;
    private CardView parcelCard;
    private CardView payCard;
    TextView tvBookStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this);
        spinner = findViewById(R.id.spin_destination);
        btnProceed = findViewById(R.id.btnProceed);
        btnVerifyPay = findViewById(R.id.btnVerifyPay);
        bookCard = findViewById(R.id.bookCard);
        payCard = findViewById(R.id.payCard);
        tvBookStatus = findViewById(R.id.tvBookStatus);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent() != null){
            myRide = (Ride) getIntent().getSerializableExtra("MYRIDE");
            booktype = (String) getIntent().getStringExtra("TYPE");
        }

        if(myRide == null){
            Toast.makeText(this,"Failed. Try again.",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnProceed.setOnClickListener(v -> {
            bookRide();
        });

        btnVerifyPay.setOnClickListener(v -> {
            showPaymentCard();
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                     destination = myRide.getDestinations().get(position-1);
                     showMessage(destination.getDestination());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                showMessage("Select destination");
            }
        });

        populateDestinations();

        if(myRide.getHas_booked()){
            btnProceed.setVisibility(View.GONE);
            tvBookStatus.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);
        }

        if(myRide.getHas_paid()){
            btnVerifyPay.setVisibility(View.GONE);
        }
    }

    private void bookRide() {
        progressDialog.setMessage("Booking Ride ...");
        progressDialog.show();
        ApiClient.with(this)
                .getApiService()
                .bookRide(destination.getId(),myRide.getId(),0)
                .enqueue(new Callback<BookStatus>() {
                    @Override
                    public void onResponse(Call<BookStatus> call, Response<BookStatus> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            if(response.body().success){
                                showMessage("Your ride has been booked successfully. Make payment to confirm.");
                                showPaymentCard();
                            }else{
                                showMessage(response.body().message);
                            }

                        }else{
                            showMessage("Could not process your request at this time.");
                        }
                    }

                    @Override
                    public void onFailure(Call<BookStatus> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void showPaymentCard() {
        spinner.setVisibility(View.GONE);
        btnProceed.setVisibility(View.GONE);
        tvBookStatus.setVisibility(View.VISIBLE);

        progressDialog.setMessage("Verifying payment status ...");
        progressDialog.show();

        ApiClient.with(this)
                .getApiService()
                .confirmRide(myRide.getId())
                .enqueue(new Callback<BookStatus>() {
                    @Override
                    public void onResponse(Call<BookStatus> call, Response<BookStatus> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            if(response.body().success){
                                showMessage(response.body().message);
                                finish();
                            }else{
                                showMessage(response.body().message);
                            }

                        }else{
                            showMessage("Could not process your request at this time.");
                        }
                    }

                    @Override
                    public void onFailure(Call<BookStatus> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void populateDestinations() {
        List<String> destinations = new ArrayList<>();
        destinations.add(" --  Select Destination --");
        for(Destination destination : myRide.getDestinations())
            destinations.add(String.format("%s - KSh.%s",destination.getDestination(),destination.getAmount()));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.spinner_ride_row,
                destinations
        );
        spinner.setAdapter(adapter);
    }

    private void showMessage(String message) {
        Toast.makeText(this,""+message,Toast.LENGTH_SHORT).show();
    }
}
