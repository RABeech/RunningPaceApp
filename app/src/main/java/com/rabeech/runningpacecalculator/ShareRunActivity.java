package com.rabeech.runningpacecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShareRunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_run);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String content = "Sharing is caring!";
        TextView share_run_content = (TextView) findViewById(R.id.share_run_content);
        share_run_content.setText(content);
    }

    public void returnHome(View view) {
        Intent in = new Intent(this, Main2Activity.class);
        startActivity(in);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
