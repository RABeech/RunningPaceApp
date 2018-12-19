package com.rabeech.runningpacecalculator;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rabeech.runningpacecalculator.data.DataSource;

import java.util.ArrayList;
import java.util.List;

public class SavedRunsActivity extends AppCompatActivity {

    private List<DataSource> listOfData;
    private String error = "Dummy Data to hold place value until fix had been made";
    private String error2 = "6/26/2017 4:40pm";

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    DatabaseReference databaseRuns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_runs);

        databaseRuns = FirebaseDatabase.getInstance().getReference("runs");

        listOfData = new ArrayList<>();

        final DataSource badLoad = new DataSource(error, error2);

        recyclerView = (RecyclerView) findViewById(R.id.rec_list_activity);
        layoutInflater = getLayoutInflater();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        for(int i = 1; i<25; i++){
            listOfData.add(badLoad);
        }

        adapter = new CustomAdapter(listOfData);
        recyclerView.setAdapter(adapter);

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        private List<DataSource> listOfData;

        public CustomAdapter(List<DataSource> listOfData) {
            this.listOfData = listOfData;
        }

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_data, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

            DataSource currentItem = listOfData.get(position);

            holder.srTime.setText(currentItem.getPace());
            holder.srDate.setText(currentItem.getDate());

        }

        @Override
        public int getItemCount() {
            return listOfData.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder{

            private TextView srTime;
            private TextView srDate;
            private ViewGroup container;

            public CustomViewHolder(View itemView) {
                super(itemView);
                this.srTime = (TextView) itemView.findViewById(R.id.lbl_distance);
                this.srDate = (TextView) itemView.findViewById(R.id.lbl_date_and_time);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_list_item);
            }

        }
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
