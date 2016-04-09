package com.sweedelight.www.sweedelight;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

public class RewardPoints extends AppCompatActivity {


    private List<List<String>> data = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_points);
        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // initialize list view
        ListView listView = (ListView)findViewById(R.id.reward_points_list_view);
        prepareData();
        listView.setAdapter(new RewardPointsListAdapter(this,data));
    }

    // temporary data
    private void prepareData(){
        List<String> first = new ArrayList<String>();
        first.add("449");
        first.add("10/02/2016");
        first.add("100");
        first.add("Increased");
        first.add("Order");
        first.add("Pending -> Completed");

        List<String> second = new ArrayList<String>();
        second.add("448");
        second.add("10/02/2016");
        second.add("100");
        second.add("Increased");
        second.add("Order");
        second.add("Points earned for Referring: Praveen B");

        data.add(first);
        data.add(second);
        data.add(first);
        data.add(second);
        data.add(first);
        data.add(second);
        data.add(first);
        data.add(second);
    }

}
