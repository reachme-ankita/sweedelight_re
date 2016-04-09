package com.sweedelight.www.sweedelight;

/**
 * Created by Ankita on 30-03-2016.
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;



public class GetExample {
    String newText;
    GetExample(String newText)
    {
        this.newText= newText;
    }
    public static void main (String args[])
    {

        StringBuilder sbParams;

        String charset = "UTF-8";
        String param_string;

        String API_URL = "http://www.sweedelight.com/index.php";
        String API_KEY = "sweedelight800";
        String method_type;
        HashMap<String, String> params;
        int responseCode;
        String token;
        String response=null;
        HttpURLConnection urlConnection;
        BufferedReader bufferedReader = null;
        URL url = null;


        // Create parameters
        params = new HashMap<>();
        params.put("api_key", API_KEY);
        //call the getproductlist method
        params.put("rt","a/product/product");
        params.put("product_id","600");

        // Convert parameters to string
        sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet())
        {
            try {
                if(i==0)
                {
                    sbParams.append("?");
                }
                else
                {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), charset));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            i++;
        }


        // GET REQUEST
        try
        {

            url = new URL(API_URL + sbParams.toString());
            System.out.println("\nURL: " + "\n" + url + "\n\n");

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            responseCode = urlConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode +"\n");

            try
            {
                //BufferedReader bufferedReader;
                if(responseCode==200)
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                else
                    bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                System.out.println("Response: " + "\n");
                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line).append("\n");
                    System.out.println(line);
                }
                bufferedReader.close();
                response = stringBuilder.toString();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                bufferedReader.close();
                urlConnection.disconnect();
            }
        }
        catch (Exception e)

        {
            e.printStackTrace();
        }

    }
}
