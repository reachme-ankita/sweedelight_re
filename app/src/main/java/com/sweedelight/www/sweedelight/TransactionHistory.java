package com.sweedelight.www.sweedelight;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {


    private List<List<String>> data = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // initialize list view
        ListView listView = (ListView)findViewById(R.id.transaction_history_list_view);
        prepareData();
        listView.setAdapter(new RewardPointsListAdapter(this,data));
    }

    // temporary data
    private void prepareData(){
        List<String> first = new ArrayList<String>();
        first.add("211");
        first.add("09/02/2016");
        first.add("reward_points");
        first.add("Rs  0.00");
        first.add("Rs 10.00");
        first.add("Reward points 10(Rs 10.00) enrolled for order #444.");

        List<String> second = new ArrayList<String>();
        second.add("212");
        second.add("09/02/2016");
        second.add("reward_points");
        second.add("Rs 10.00");
        second.add("Rs  0.00");
        second.add("Reward points 10(Rs 10.00) redeemed for order #444.");

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
