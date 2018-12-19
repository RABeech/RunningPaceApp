package com.rabeech.runningpacecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

public class InputUserData extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user_data2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Email Suggestions: AdamBeechRAB@gmail.com", Snackbar.LENGTH_LONG)
                    .setAction(" ", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void dataEntryValdation(View view){
        TextInputLayout hourLayout = (TextInputLayout) findViewById(R.id.run_time_hours);
        TextInputLayout minuteLayout = (TextInputLayout) findViewById(R.id.run_time_minutes);
        TextInputLayout secondLayout = (TextInputLayout) findViewById(R.id.run_time_seconds);
        TextInputLayout distanceLayout = (TextInputLayout) findViewById(R.id.run_time_distance);

        EditText hourText = (EditText) findViewById(R.id.run_time_hours_field);
        EditText minuteText = (EditText) findViewById(R.id.run_time_minutes_field);
        EditText secondText = (EditText) findViewById(R.id.run_time_seconds_field);
        EditText runDistance = (EditText) findViewById(R.id.run_time_distance_field);

        boolean isValid = true;

        if(hourText.getText().toString().isEmpty()){
            hourLayout.setError("Must enter a number: 0 or greater");
            isValid = false;
        } else {
            hourLayout.setErrorEnabled(false);
        }
        if(minuteText.getText().toString().isEmpty()){
            minuteLayout.setError("Must enter a number");
            isValid = false;
        } else {
            minuteLayout.setErrorEnabled(false);
        }
        if(secondText.getText().toString().isEmpty()){
            secondLayout.setError("Must enter a number");
            isValid = false;
        } else {
            secondLayout.setErrorEnabled(false);
        }
        if(runDistance.getText().toString().isEmpty()){
            distanceLayout.setError("Must enter a number");
            isValid = false;
        } else {
            distanceLayout.setErrorEnabled(false);
        }



        if(isValid){
            startResultActivity(view);
        }


    }
    public void startResultActivity(View view){
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle b = new Bundle();

        EditText hourText = (EditText) findViewById(R.id.run_time_hours_field);
        EditText minuteText = (EditText) findViewById(R.id.run_time_minutes_field);
        EditText secondText = (EditText) findViewById(R.id.run_time_seconds_field);
        EditText runDistance = (EditText) findViewById(R.id.run_time_distance_field);

        double hourInput = Double.parseDouble(hourText.getText().toString());
        double minuteInput = Double.parseDouble(minuteText.getText().toString());
        double secondInput = Double.parseDouble(secondText.getText().toString());
        double runInput = Double.parseDouble(runDistance.getText().toString());



        b.putDouble("hourInput", hourInput);
        b.putDouble("minuteInput", minuteInput);
        b.putDouble("secondInput", secondInput);
        b.putDouble("runInput", runInput);
        intent.putExtras(b);

        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                Intent homeIn = new Intent(this, Main2Activity.class);
                startActivity(homeIn);
                break;
            case R.id.nav_run:
                break;
            case R.id.nav_saved_runs:
                Intent saveIn = new Intent(this, SavedRunsActivity.class);
                startActivity(saveIn);
                break;
            case R.id.nav_rr:
                Intent runResIn = new Intent(this, RunningResources.class);
                startActivity(runResIn);
                break;
            case R.id.nav_share:
                Intent shareIn = new Intent(this, ShareRunActivity.class);
                startActivity(shareIn);
                break;
            case R.id.nav_send:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"adambeechrab@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RPC User Suggestions");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Yo dawg, try this! How about..");
                startActivity(emailIntent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
