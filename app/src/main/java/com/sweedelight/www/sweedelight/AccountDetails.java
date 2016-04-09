package com.sweedelight.www.sweedelight;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AccountDetails extends AppCompatActivity implements AsyncResponse {

    TextView login_name;
    EditText first_name,last_name,email,mobile,fax;
    String login_name_string,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Retrieve TextView and EditTexts
        login_name = (TextView)findViewById(R.id.login_name);
        first_name = (EditText)findViewById(R.id.input_first_name);
        last_name = (EditText)findViewById(R.id.input_last_name);
        email = (EditText)findViewById(R.id.input_email);
        mobile = (EditText)findViewById(R.id.input_mobile);
        fax = (EditText)findViewById(R.id.input_fax);

        //retrieve login_name and token from shared preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        login_name_string = settings.getString("login_name","");
        token = settings.getString("token","");

        // make the api call
        HashMap<String,String> params = new HashMap<>();
        params.put("rt","a/account/login");
        params.put("token",token);
        HTTPTask api_call = new HTTPTask("POST",params,this);
        api_call.execute();

    }


    // called after values are returned
    @Override
    public void processFinish(String output) {
            if(output==null)
            {
                login_name.setText("ERROR");
            }
            else
            {
               try{
                   JSONObject root = new JSONObject(output);
                   JSONObject fields = root.getJSONObject("fields");
                   JSONObject firstName = fields.getJSONObject("firstname");
                   JSONObject lastName = fields.getJSONObject("lastname");
                   JSONObject emailObject = fields.getJSONObject("email");
                   JSONObject telephone = fields.getJSONObject("telephone");
                   JSONObject faxObject = fields.getJSONObject("fax");

                   login_name.setText(login_name_string);
                   first_name.setText(firstName.getString("value"));
                   last_name.setText(lastName.getString("value"));
                   email.setText(emailObject.getString("value"));
                   mobile.setText(telephone.getString("value"));
                   fax.setText(faxObject.getString("value"));
               }
               catch(JSONException e)
               {
                   e.printStackTrace();
               }
            }
    }
}
