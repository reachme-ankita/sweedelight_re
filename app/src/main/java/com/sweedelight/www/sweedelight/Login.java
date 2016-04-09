package com.sweedelight.www.sweedelight;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;



public class Login extends AppCompatActivity implements AsyncResponse{

    String API_URL = "http://www.sweedelight.com/index.php";
    String API_KEY ="sweedelight800";
    TextView status;
    TextView username;
    TextView password;
    String username_string;
    String password_string;
    HashMap<String,String> params;
    JSONObject jsonobject;

    SharedPreferences settings;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        status = (TextView)findViewById(R.id.status_text);
        username = (TextView)findViewById(R.id.input_login_name);
        password = (TextView)findViewById(R.id.input_password);
        params = new HashMap<>();

        settings = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        editor = settings.edit();

    }

    public void onClickLogin(View v)
    {
        username_string=username.getText().toString();
        password_string=password.getText().toString();
        params.put("rt","a/account/login");
        params.put("email",username_string);
        params.put("password",password_string);

        HTTPTask api_call = new HTTPTask("POST",params,this);
        api_call.execute();

    }

    @Override
    public void processFinish(String response) {
        if(response!=null)
        {
            // add code to store token in shared preferences
            try{
                jsonobject = new JSONObject(response);
                status.setText(jsonobject.toString());

                // store login_name and token in shared preferences
                String token = jsonobject.getString("token");
                editor.putString("login_name",username_string);
                editor.putString("token",token);
                editor.commit();
            }
            catch(JSONException e)
            {
                Log.e("ERROR", e.getMessage(), e);
                response = "JSON ERROR";
                status.setText(response);
            }

        }
        else{
            response = "THERE WAS AN ERROR";
            status.setText(response);
        }
        Log.i("INFO", response);
    }

    /*
    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        StringBuilder sbParams;
        String charset="UTF-8";
        String param_string;

        protected void onPreExecute() {

            sbParams = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                try {
                    if (i != 0){
                        sbParams.append("&");
                    }
                    sbParams.append(key).append("=")
                            .append(URLEncoder.encode(params.get(key), charset));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }

        }

        protected String doInBackground(Void... urls) {


            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Add parameters
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
                param_string = sbParams.toString();
                os.writeBytes(param_string);
                os.flush();
                os.close();



                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                   // urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response!=null)
            {
                // add code to store token in shared preferences
                try{
                    jsonobject = new JSONObject(response);
                    status.setText(jsonobject.toString());

                    // store login_name and token in shared preferences
                    String token = jsonobject.getString("token");
                    editor.putString("login_name",username_string);
                    editor.putString("token",token);
                    editor.commit();
                }
                catch(JSONException e)
                {
                    Log.e("ERROR", e.getMessage(), e);
                    response = "JSON ERROR";
                    status.setText(response);
                }

            }
            else{
                response = "THERE WAS AN ERROR";
                status.setText(response);
            }
            Log.i("INFO", response);

        }
    }
    */
}
