package com.rabeech.runningpacecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


    }

    public void returnSR(View view) {
        Intent in = new Intent(this, SavedRunsActivity.class);
        startActivity(in);
    }
}
