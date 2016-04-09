package com.sweedelight.www.sweedelight;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WishList extends AppCompatActivity {


    private List<List<String>> data = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // initialize list view
        ListView listView = (ListView)findViewById(R.id.wish_list_list_view);
        prepareData();
        listView.setAdapter(new WishListListAdapter(this, data));
    }

    // temporary data
    private void prepareData(){
        List<String> first = new ArrayList<String>();
        first.add("Spl Malai Chamcham");
        first.add("AB107");
        first.add("Rs. 50.00");
        first.add("09/02/2016");


        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);
        data.add(first);

    }

    // on click listener for add
    public void onClickAdd(View v){
        // code to move item from wish list to cart
        Toast.makeText(WishList.this,"Item Added",Toast.LENGTH_SHORT).show();
    }

    // on click listener for remove button
    public void onClickDelete(View v){
        // code to remove item from wish list
        Toast.makeText(WishList.this,"Item Removed",Toast.LENGTH_SHORT).show();
    }

}
