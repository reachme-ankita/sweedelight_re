package com.sweedelight.www.sweedelight;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;
public class WishListListAdapter extends BaseAdapter {

    private Context mContext;
    private List<List<String>> data = new ArrayList<List<String>>();


    public static class WishListViewHolder {
        ImageView wish_list_image;
        TextView wish_list_name, wish_list_model, wish_list_unit_price, wish_list_date_added;
        ImageButton wish_list_add_button, wish_list_delete_button;
        int position;
    }


    public WishListListAdapter(Context c, List<List<String>> data){
        mContext = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WishListViewHolder viewHolder;
        View cell = convertView;
        if (cell == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.wish_list_list_layout, parent, false);
            viewHolder = new WishListViewHolder();
            viewHolder.wish_list_name=(TextView)cell.findViewById(R.id.wish_list_name);
            viewHolder.wish_list_model =(TextView)cell.findViewById(R.id.wish_list_model);
            viewHolder.wish_list_unit_price =(TextView)cell.findViewById(R.id.wish_list_unit_price);
            viewHolder.wish_list_date_added =(TextView)cell.findViewById(R.id.wish_list_date_added);
            viewHolder.wish_list_image=(ImageView)cell.findViewById(R.id.wish_list_image);
            viewHolder.wish_list_add_button=(ImageButton)cell.findViewById(R.id.wish_list_add_button);
            viewHolder.wish_list_delete_button=(ImageButton)cell.findViewById(R.id.wish_list_delete_button);
            viewHolder.position = position;
            cell.setTag(viewHolder);
        } else {
            viewHolder = (WishListViewHolder) cell.getTag();
        }


        // set the Text for the element
        List<String> currentData = data.get(position);
        viewHolder.wish_list_name.setText(currentData.get(0));
        viewHolder.wish_list_model.setText(currentData.get(1));
        viewHolder.wish_list_unit_price.setText(currentData.get(2));
        viewHolder.wish_list_date_added.setText(currentData.get(3));
        viewHolder.wish_list_image.setImageResource(R.drawable.sample_image);
        viewHolder.wish_list_add_button.setFocusable(false);
        viewHolder.wish_list_delete_button.setFocusable(false);
        return cell;

    }
}
