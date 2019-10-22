package com.eryxlabs.fiderides.ui.login;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private ProgressDialog progressDialog;
    private TextView tvMessage;

    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvMessage = findViewById(R.id.tvMessage);

        loginViewModel.accessTokenLogin.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s!=null){


                    Cache.setAuthToken(LoginActivity.this, s);
                    if(Cache.hasAuthToken(LoginActivity.this)){
                        Cache.setAuthToken(LoginActivity.this,s);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }else{
                        showMessage("Login failed");
                    }

                }
            }
        });

        btnLogin.setOnClickListener(v -> attemptLogin());

    }

    private void attemptLogin() {
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();
        if(email.isEmpty() || pass.isEmpty()){
            showMessage("Empty credentials are not allowed");
            return;
        }
        tvMessage.setVisibility(View.GONE);
        progressDialog.setMessage("Authenticating user ...");
        progressDialog.show();


        loginViewModel.attemptLogin(email,pass,1);


    }


    private void showToken(UserToken body) {
        Cache.setAuthToken(this, body.authToken);
        if(Cache.hasAuthToken(this)){
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

    @Override
    public void onBackPressed() {
        showMessage("Enter your login details to enjoy cheap rides.");
    }
}
