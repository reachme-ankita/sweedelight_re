package com.sweedelight.www.sweedelight;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

public class AddressBookExpandableListAdapter extends BaseExpandableListAdapter{

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public AddressBookExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData)
    {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.address_list_header, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_header);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(headerTitle);

        ImageButton imageButton = (ImageButton)convertView.findViewById(R.id.delete_button);
        imageButton.setFocusable(false);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String firstName = (String) getChild(groupPosition, 0);
        final String lastName = (String) getChild(groupPosition, 1);
        final String company = (String) getChild(groupPosition, 2);
        final String address1 = (String) getChild(groupPosition, 3);
        final String address2 = (String) getChild(groupPosition, 4);
        final String areaName = (String) getChild(groupPosition, 5);
        final String cityName = (String) getChild(groupPosition, 6);
        final String stateName = (String) getChild(groupPosition, 7);
        final String zipCode = (String) getChild(groupPosition, 8);
        //final String defaultAddress = (String) getChild(groupPosition, 9);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.address_list_child, null);
        }

        TextView textView= (TextView) convertView.findViewById(R.id.first_name);
        textView.setText(firstName);

        textView= (TextView) convertView.findViewById(R.id.last_name);
        textView.setText(lastName);

        textView= (TextView) convertView.findViewById(R.id.company_name);
        textView.setText(company);

        textView= (TextView) convertView.findViewById(R.id.address_1);
        textView.setText(address1);

        textView= (TextView) convertView.findViewById(R.id.address_2);
        textView.setText(address2);

        textView= (TextView) convertView.findViewById(R.id.area_name);
        textView.setText(areaName);

        textView= (TextView) convertView.findViewById(R.id.city_name);
        textView.setText(cityName);

        textView= (TextView) convertView.findViewById(R.id.state_name);
        textView.setText(stateName);

        textView= (TextView) convertView.findViewById(R.id.zip_code);
        textView.setText(zipCode);

        // yet to add DEFAULT ADDRESS
        //textView= (TextView) convertView.findViewById(R.id.default_address);
        //textView.setText(defaultAddress);

        Button button = (Button)convertView.findViewById(R.id.edit_button);
        button.setFocusable(false);

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
