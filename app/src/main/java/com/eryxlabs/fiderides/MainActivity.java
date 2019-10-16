package com.eryxlabs.fiderides;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.eryxlabs.fiderides.ui.LoginActivity;
import com.eryxlabs.fiderides.utils.Cache;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!Cache.hasAuthToken(this)){
            logout();
            return;
        }

        progressDialog =  new ProgressDialog(this);
        progressDialog.setCancelable(false);
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
