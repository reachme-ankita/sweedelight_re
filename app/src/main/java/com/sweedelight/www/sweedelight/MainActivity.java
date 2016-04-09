package com.sweedelight.www.sweedelight;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    //called when user clicks on About Us button
    public void startAboutUs(View view){
        startActivity(new Intent(this, AboutUs.class));
    }

    //called when user clicks on Register button
    public void startRegister(View view){
        startActivity(new Intent(this,Register.class));
    }

    //called when user clicks on Review button
    public void startReview(View view){
        startActivity(new Intent(this,Review.class));
    }

    //called when user clicks on AccountDashboard button
    public void startAccount(View view){
        startActivity(new Intent(this,AccountDashboard.class));
    }

    //called when user clicks on AccountDashboard button
    public void startLogin(View view){
        startActivity(new Intent(this,Login.class));
    }

    public void search(View view){
        startActivity(new Intent(this,Search2.class));
    }
}
