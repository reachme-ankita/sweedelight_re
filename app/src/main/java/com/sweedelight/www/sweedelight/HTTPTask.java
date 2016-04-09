package com.sweedelight.www.sweedelight;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class HTTPTask extends AsyncTask<Void, Void, String> {

    StringBuilder sbParams;
    String charset = "UTF-8";
    String param_string;
    String API_URL = "http://www.sweedelight.com/index.php";
    String API_KEY = "sweedelight800";

    String method_type;
    HashMap<String, String> params;

    AsyncResponse delegate;
    int responseCode;

    HTTPTask(String method_type, HashMap<String, String> params, AsyncResponse delegate) {
        this.method_type = method_type;
        this.params = params;
        this.params.put("api_key", API_KEY);
        this.delegate = delegate;
    }

    protected void onPreExecute() {

        // convert params to srting builder
        sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0) {
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
            HttpURLConnection urlConnection;

            // if method type is POST then pass params thru output stream
            if (method_type.equals("POST")) {
                URL url = new URL(API_URL);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
                param_string = sbParams.toString();
                os.writeBytes(param_string);
                os.flush();
                os.close();
                responseCode = urlConnection.getResponseCode();

            } else    //if method type is get then add params to URL
            {
                String full_url = API_URL + "?";
                int i = 0;
                for (String key : params.keySet()) {
                    if (i != 0) {
                        full_url = full_url + "&";
                    }
                    full_url = full_url + key + "=" + params.get(key);
                    i++;
                }
                URL url = new URL(full_url);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(false);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();
                responseCode = urlConnection.getResponseCode();
                //throw new Exception("Response Code: "+responseCode);
            }

            try {
                BufferedReader bufferedReader;
                if(responseCode==200)
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                else
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                Log.i("RETURNED JSON", stringBuilder.toString());
                return stringBuilder.toString();
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }

    }

    protected void onPostExecute(String response) {
        delegate.processFinish(response);
        Log.i("Response Code","" +responseCode);
    }
}