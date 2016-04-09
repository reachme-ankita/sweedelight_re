package com.sweedelight.www.sweedelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressBook extends AppCompatActivity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.address_book_expandable_list_view);

        // preparing list data
        prepareListData();

        listAdapter = new AddressBookExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    // on click listeners for the Edit Buttons
    public void onClickEdit(View v) {

        Intent intent = new Intent(AddressBook.this,EditAddressBook.class);
        View rootView = v.getRootView();

        TextView textView = (TextView)rootView.findViewById(R.id.first_name);
        String string = textView.getText().toString();
        intent.putExtra("first_name",string);

        textView = (TextView)rootView.findViewById(R.id.last_name);
        string = textView.getText().toString();
        intent.putExtra("last_name",string);

        textView = (TextView)rootView.findViewById(R.id.company_name);
        string = textView.getText().toString();
        intent.putExtra("company_name",string);

        textView = (TextView)rootView.findViewById(R.id.address_1);
        string = textView.getText().toString();
        intent.putExtra("address_1",string);

        textView = (TextView)rootView.findViewById(R.id.address_2);
        string = textView.getText().toString();
        intent.putExtra("address_2",string);

        textView = (TextView)rootView.findViewById(R.id.area_name);
        string = textView.getText().toString();
        intent.putExtra("area_name",string);

        textView = (TextView)rootView.findViewById(R.id.city_name);
        string = textView.getText().toString();
        intent.putExtra("city_name",string);

        textView = (TextView)rootView.findViewById(R.id.state_name);
        string = textView.getText().toString();
        intent.putExtra("state_name",string);

        textView = (TextView)rootView.findViewById(R.id.zip_code);
        string = textView.getText().toString();
        intent.putExtra("zip_code",string);

        intent.putExtra("Button","Edit"); //implying edit address

        startActivity(intent);
    }

    // on click listener for the add button
    public void onClickAddAddress(View v)
    {
        Intent intent = new Intent(this,EditAddressBook.class);
        intent.putExtra("Button","Add");
        startActivity(intent);
    }

    // temporary function to add data
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Address 1");
        listDataHeader.add("Address 2");


        // Adding child data
        List<String> address1 = new ArrayList<String>();
        address1.add("Akshay");
        address1.add("Ravichandran");
        address1.add("SweeDelight");
        address1.add("43, First Avenue");
        address1.add("2nd Main Road");
        address1.add("Banashankari");
        address1.add("Bangalore");
        address1.add("Karnataka");
        address1.add("560021");

        List<String> address2 = new ArrayList<String>();
        address2.add("Lohith");
        address2.add("Haresh");
        address2.add("HCL");
        address2.add("27, Fourth Street");
        address2.add("3rd Main Road");
        address2.add("Basavanagudi");
        address2.add("Bangalore");
        address2.add("Karnataka");
        address2.add("520645");

        listDataChild.put(listDataHeader.get(0), address1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), address2);

    }
}

