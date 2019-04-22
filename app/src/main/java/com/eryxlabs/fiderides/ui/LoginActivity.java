package com.eryxlabs.fiderides.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eryxlabs.fiderides.MainActivity;
import com.eryxlabs.fiderides.R;
import com.eryxlabs.fiderides.models.UserToken;
import com.eryxlabs.fiderides.utils.ApiClient;
import com.eryxlabs.fiderides.utils.Cache;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
//        Test login
        ApiClient.with(this)
                .getApiService()
                .getLoginToken("john.doe@domain.com","passme")
                .enqueue(new Callback<UserToken>() {
                    @Override
                    public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                        showToken(response.body());
                    }

                    @Override
                    public void onFailure(Call<UserToken> call, Throwable t) {
                        showMessage(t.getMessage());
                    }
                });
    }


    private void showToken(UserToken body) {
        Cache.setAuthToken(this,body.authToken);
        if(Cache.hasAuthToken(this)){
            showMessage(Cache.getAuthToken(this));
            Cache.setAuthToken(this,body.authToken);
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }else{
            showMessage("Login failed");
        }
    }

    private void showMessage(String authToken) {
        Toast.makeText(this,""+authToken,Toast.LENGTH_SHORT).show();
    }

}
