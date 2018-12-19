package com.rabeech.runningpacecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rabeech.runningpacecalculator.data.DataSource;

public class ResultActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private double hourInput = 0, minuteInput = 0, secondInput = 0, runInput = 0;

    private String sr_hour = "";
    private String sr_minute = "";
    private String sr_second = "";
    private String sr_distance = "";
    private String sr_pace = "";
    private String sr_date = "";

    private Button srBTN, btnCal, btnView, btnSave;

    FloatingActionButton fab, fabCal, fabView, fabSave;
    Animation fabOpen, fabClose, btnOpen, btnClose, fabRClock, fabRCounter, textOpen, textClose;
    TextView tvTease;
    boolean isOpen = false;

    DatabaseReference databaseRuns;
    private List<DataSource> listOfData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseRuns = FirebaseDatabase.getInstance().getReference("runs");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabCal = (FloatingActionButton) findViewById(R.id.fab_cal_again);
        fabView = (FloatingActionButton)findViewById(R.id.fab_view_save);
        fabSave = (FloatingActionButton)findViewById(R.id.fab_save_run);

        btnCal = (Button) findViewById(R.id.btn_cal_again);
        btnView = (Button) findViewById(R.id.btn_view_save);
        btnSave = (Button) findViewById(R.id.btn_save_run);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        btnOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_open);
        btnClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_close);
        fabRClock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fabRCounter = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_counter);
        textOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tease_open);
        textClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tease_close);
        textClose.setStartOffset(4500);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fab.startAnimation(fabRCounter);
                    fabCal.startAnimation(fabClose);
                    fabView.startAnimation(fabClose);
                    fabSave.startAnimation(fabClose);
                    btnCal.startAnimation(btnClose);
                    btnView.startAnimation(btnClose);
                    btnSave.startAnimation(btnClose);
                    fabCal.setClickable(false);
                    fabView.setClickable(false);
                    fabSave.setClickable(false);
                    btnCal.setClickable(false);
                    btnView.setClickable(false);
                    btnSave.setClickable(false);
                    isOpen = false;
                }else{
                    fab.startAnimation(fabRClock);
                    fabCal.startAnimation(fabOpen);
                    fabView.startAnimation(fabOpen);
                    fabSave.startAnimation(fabOpen);
                    btnCal.startAnimation(btnOpen);
                    btnView.startAnimation(btnOpen);
                    btnSave.startAnimation(btnOpen);
                    fabCal.setClickable(true);
                    fabView.setClickable(true);
                    fabSave.setClickable(true);
                    btnCal.setClickable(true);
                    btnView.setClickable(true);
                    btnSave.setClickable(true);
                    isOpen = true;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvTease = (TextView) findViewById(R.id.text_tease);
        tvTease.startAnimation(textOpen);
        tvTease.startAnimation(textClose);

        Intent it = getIntent();
        if (it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                hourInput = params.getDouble("hourInput");
                minuteInput = params.getDouble("minuteInput");
                secondInput = params.getDouble("secondInput");
                runInput = params.getDouble("runInput");

                sr_hour = params.getString("hourInput");
                sr_minute = params.getString("minuteInput");
                sr_second = params.getString("secondInput");
                sr_distance = params.getString("runInput");
            }
        }

        String result = convert(hourInput, minuteInput, secondInput, runInput);
        String display = displayString(result, hourInput, minuteInput, secondInput, runInput);
        sr_pace = display;
        sr_date = getCurrentTimeStamp();

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRunDB(sr_pace, sr_date);
                fab.startAnimation(fabRCounter);
                fabCal.startAnimation(fabClose);
                fabView.startAnimation(fabClose);
                fabSave.startAnimation(fabClose);
                btnCal.startAnimation(fabClose);
                btnView.startAnimation(fabClose);
                btnSave.startAnimation(fabClose);
                fabCal.setClickable(false);
                fabView.setClickable(false);
                fabSave.setClickable(false);
                btnCal.setClickable(false);
                btnView.setClickable(false);
                btnSave.setClickable(false);
                isOpen = false;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRunDB(sr_pace, sr_date);
                saveRunDB(sr_pace, sr_date);
                fab.startAnimation(fabRCounter);
                fabCal.startAnimation(fabClose);
                fabView.startAnimation(fabClose);
                fabSave.startAnimation(fabClose);
                btnCal.startAnimation(fabClose);
                btnView.startAnimation(fabClose);
                btnSave.startAnimation(fabClose);
                fabCal.setClickable(false);
                fabView.setClickable(false);
                fabSave.setClickable(false);
                btnCal.setClickable(false);
                btnView.setClickable(false);
                btnSave.setClickable(false);
                isOpen = false;
            }
        });

        TextView textView = (TextView) findViewById(R.id.result_view);
        textView.setText(display);
    }

    private void saveRunDB(String sr_pace, String sr_date) {

        String id = databaseRuns.push().getKey();
        DataSource srRun = new DataSource(sr_pace, sr_date);
        databaseRuns.child(id).setValue(srRun);

        Toast.makeText(this, "Run Saved", Toast.LENGTH_SHORT).show();

    }


    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("MM-dd-yyyy HH:mmaa").format(new Date());
    }

    public static String convert(double hours, double min, double second, double mile) {
        NumberFormat minFormat = new DecimalFormat("#0");
        NumberFormat secondFormat = new DecimalFormat("#0");

        hours = hours * 3600;
        min = min * 60;

        double totalTime = hours + min + second;

        double rawTime = totalTime / mile;

        double rawMin = rawTime / 60;
        double rawHours = rawMin / 60;
        double rawHoursDec = rawHours % 1;
        double rawHoursWhole = rawHours - rawHoursDec;
        rawMin = rawHoursDec * 60;
        double rawMinDec = rawMin % 1;
        double rawMinWhole = rawMin - rawMinDec;
        double rawSeconds = rawMinDec * 60;

        String temp = minFormat.format(rawMinWhole) + ":" + secondFormat.format(rawSeconds);
        return temp;
    }

    public static String displayString(String result, double hours, double min, double second, double miles) {
        NumberFormat formatter = new DecimalFormat("#0");

        String temp = "Average pace per mile for " + miles + " miles, at " + formatter.format(min) +
                " minutes and " + formatter.format(second) + " seconds is " + result + " minutes per mile!";
        return temp;
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
                Intent runIn = new Intent(this, InputUserData.class);
                startActivity(runIn);
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
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"adambeechrab@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RPC User Suggestions");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Yo dawg, try this! How about..");
                startActivity(emailIntent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void btnLunchCal(View view) {
        Intent intent = new Intent(this, InputUserData.class);
        startActivity(intent);
    }

    public void fabLunchCal(View view) {
        Intent intent = new Intent(this, InputUserData.class);
        startActivity(intent);
    }

    public void btnLunchSR(View view) {
        Intent intent = new Intent(this, SavedRunsActivity.class);
        startActivity(intent);
    }

    public void fabLaunchSR(View view) {
        Intent intent = new Intent(this, SavedRunsActivity.class);
        startActivity(intent);
    }

}
