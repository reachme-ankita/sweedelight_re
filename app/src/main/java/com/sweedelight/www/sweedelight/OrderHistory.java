package com.sweedelight.www.sweedelight;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {


    private List<List<String>> data = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // initialize list view
        ListView listView = (ListView)findViewById(R.id.order_history_list_view);
        prepareData();
        listView.setAdapter(new WishListListAdapter(this,data));
    }

    // temporary data
    private void prepareData(){
        List<String> first = new ArrayList<String>();
        first.add("#369");
        first.add("03/02/2016");
        first.add("3");
        first.add("Akshay Ravichandran");
        first.add("500.00");

        List<String> second = new ArrayList<String>();
        second.add("#214");
        second.add("05/02/2016");
        second.add("7");
        second.add("Vishal Baskaran");
        second.add("2500.00");

        data.add(first);
        data.add(second);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
    }

}
