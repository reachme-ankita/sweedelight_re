package com.sweedelight.www.sweedelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditAddressBook extends AppCompatActivity {

    Intent receivedIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address_book);
        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        receivedIntent = getIntent();

        // entry into activity after clicking edit address
        if (receivedIntent.getStringExtra("Button").equals("Edit") )
        {
            EditText editText = (EditText) findViewById(R.id.input_first_name);
            String string = receivedIntent.getStringExtra("first_name");
            editText.setText(string);

            editText = (EditText) findViewById(R.id.input_last_name);
            string = receivedIntent.getStringExtra("last_name");
            editText.setText(string);

            editText = (EditText) findViewById(R.id.input_company_name);
            string = receivedIntent.getStringExtra("company_name");
            editText.setText(string);

            editText = (EditText) findViewById(R.id.input_address_1);
            string = receivedIntent.getStringExtra("address_1");
            editText.setText(string);

            editText = (EditText) findViewById(R.id.input_address_2);
            string = receivedIntent.getStringExtra("address_2");
            editText.setText(string);

            Spinner spinner = (Spinner) findViewById(R.id.input_area);
            string = receivedIntent.getStringExtra("area_name");
            spinner.setSelection(getIndex(spinner, string));

            spinner = (Spinner) findViewById(R.id.input_city);
            string = receivedIntent.getStringExtra("city_name");
            spinner.setSelection(getIndex(spinner, string));

            spinner = (Spinner) findViewById(R.id.input_state);
            string = receivedIntent.getStringExtra("state_name");
            spinner.setSelection(getIndex(spinner, string));

            editText = (EditText) findViewById(R.id.input_zip_code);
            string = receivedIntent.getStringExtra("zip_code");
            editText.setText(string);
        }
        else
        {
            Button button = (Button)findViewById(R.id.update_button);
            button.setText("Add Address");
        }

    }

    public void onClickButton(View v){


        if(receivedIntent.getStringExtra("Button").equals("Add"))
        {
            ;// what to do if new address is added
        }
        else if(receivedIntent.getStringExtra("Button").equals("Edit"))
        {
            ;// what to do if existing address is being edited
        }
    }

    // private method for getting index
    private int getIndex(Spinner spinner, String string) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
