package com.sweedelight.www.sweedelight;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;
public class TransactionHistoryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<List<String>> data = new ArrayList<List<String>>();


    public static class TransactionHistoryViewHolder {
        TextView transaction_id, transaction_date, transaction_type, transaction_debit, transaction_credit, transaction_description;
        int position;
    }


    public TransactionHistoryListAdapter(Context c, List<List<String>> data){
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

        TransactionHistoryViewHolder viewHolder;
        View cell = convertView;
        if (cell == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.transaction_history_list_layout, parent, false);
            viewHolder = new TransactionHistoryViewHolder();
            viewHolder.transaction_id =(TextView)cell.findViewById(R.id.transaction_id);
            viewHolder.transaction_date =(TextView)cell.findViewById(R.id.transaction_date);
            viewHolder.transaction_type =(TextView)cell.findViewById(R.id.transaction_type);
            viewHolder.transaction_debit =(TextView)cell.findViewById(R.id.transaction_debit);
            viewHolder.transaction_credit =(TextView)cell.findViewById(R.id.transaction_credit);
            viewHolder.transaction_description =(TextView)cell.findViewById(R.id.transaction_description);
            viewHolder.position = position;
            cell.setTag(viewHolder);
        } else {
            viewHolder = (TransactionHistoryViewHolder) cell.getTag();
        }


        // set the Text for the element
        List<String> currentData = data.get(position);
        viewHolder.transaction_id.setText(currentData.get(0));
        viewHolder.transaction_date.setText(currentData.get(1));
        viewHolder.transaction_type.setText(currentData.get(2));
        viewHolder.transaction_debit.setText(currentData.get(3));
        viewHolder.transaction_credit.setText(currentData.get(4));
        viewHolder.transaction_description.setText(currentData.get(5));
        return cell;

    }
}
