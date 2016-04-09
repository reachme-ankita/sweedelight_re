package com.sweedelight.www.sweedelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AccountDashboard extends AppCompatActivity {

    private Integer[] mImages = {
            R.drawable.account, R.drawable.password,
            R.drawable.address, R.drawable.wish,
            R.drawable.order, R.drawable.transaction,
            R.drawable.reward, R.drawable.newsletter,
            R.drawable.logout};

    private String[] mLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_dashboard);

        // set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display home button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get label data
        mLabels = getResources().getStringArray(R.array.account_list_labels);

        //initialize list view
        ListView listView = (ListView) findViewById(R.id.account_dashboard_list_view);
        listView.setAdapter(new AccountDashboardListAdapter(this,mImages,mLabels));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch(position)
                {
                    case 0: // Account Details
                            startActivity(new Intent(AccountDashboard.this,AccountDetails.class));
                            break;
                    case 1: // Change password
                            startActivity(new Intent(AccountDashboard.this,ChangePassword.class));
                            break;
                    case 2: // Address Book
                            startActivity(new Intent(AccountDashboard.this,AddressBook.class));
                            break;
                    case 3: // Wish List
                            startActivity(new Intent(AccountDashboard.this,WishList.class));
                            break;
                    case 4: // Order History
                            startActivity(new Intent(AccountDashboard.this,OrderHistory.class));
                            break;
                    case 5: // Transaction History
                            startActivity(new Intent(AccountDashboard.this,TransactionHistory.class));
                            break;
                    case 6: // Rewards Points
                            startActivity(new Intent(AccountDashboard.this,RewardPoints.class));
                            break;
                    case 7: // Newsletter Subscription
                            startActivity(new Intent(AccountDashboard.this,NewsletterSubscription.class));
                            break;
                    case 8: // Log Out
                            break;
                    default: break;
                }
            }
        });
    }

}
