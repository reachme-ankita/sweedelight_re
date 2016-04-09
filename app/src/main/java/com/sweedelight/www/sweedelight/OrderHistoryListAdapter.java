package com.sweedelight.www.sweedelight;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;
public class OrderHistoryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<List<String>> data = new ArrayList<List<String>>();


    public static class OrderHistoryViewHolder {
        TextView order_id, order_date, order_products, order_customer_name, order_total;
        int position;
    }


    public OrderHistoryListAdapter(Context c, List<List<String>> data){
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

        OrderHistoryViewHolder viewHolder;
        View cell = convertView;
        if (cell == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.order_history_list_layout, parent, false);
            viewHolder = new OrderHistoryViewHolder();
            viewHolder.order_id=(TextView)cell.findViewById(R.id.order_id);
            viewHolder.order_date =(TextView)cell.findViewById(R.id.order_date);
            viewHolder.order_products =(TextView)cell.findViewById(R.id.order_products);
            viewHolder.order_customer_name =(TextView)cell.findViewById(R.id.order_customer_name);
            viewHolder.order_total =(TextView)cell.findViewById(R.id.order_total);
            viewHolder.position = position;
            cell.setTag(viewHolder);
        } else {
            viewHolder = (OrderHistoryViewHolder) cell.getTag();
        }


        // set the Text for the element
        List<String> currentData = data.get(position);
        viewHolder.order_id.setText(currentData.get(0));
        viewHolder.order_date.setText(currentData.get(1));
        viewHolder.order_products.setText(currentData.get(2));
        viewHolder.order_customer_name.setText(currentData.get(3));
        viewHolder.order_total.setText(currentData.get(4));
        return cell;

    }
}
