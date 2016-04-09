package com.sweedelight.www.sweedelight;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;
public class RewardPointsListAdapter extends BaseAdapter {

    private Context mContext;
    private List<List<String>> data = new ArrayList<List<String>>();


    public static class RewardPointsViewHolder {
        TextView reward_points_id, reward_points_date, reward_points_amount, reward_points_action_type, reward_points_modified_by, reward_points_status;
        int position;
    }


    public RewardPointsListAdapter(Context c, List<List<String>> data){
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

        RewardPointsViewHolder viewHolder;
        View cell = convertView;
        if (cell == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.reward_points_list_layout, parent, false);
            viewHolder = new RewardPointsViewHolder();
            viewHolder.reward_points_id =(TextView)cell.findViewById(R.id.reward_points_id);
            viewHolder.reward_points_date =(TextView)cell.findViewById(R.id.reward_points_date);
            viewHolder.reward_points_amount =(TextView)cell.findViewById(R.id.reward_points_amount);
            viewHolder.reward_points_action_type =(TextView)cell.findViewById(R.id.reward_points_action_type);
            viewHolder.reward_points_modified_by =(TextView)cell.findViewById(R.id.reward_points_modified_by);
            viewHolder.reward_points_status =(TextView)cell.findViewById(R.id.reward_points_status);
            viewHolder.position = position;
            cell.setTag(viewHolder);
        } else {
            viewHolder = (RewardPointsViewHolder) cell.getTag();
        }


        // set the Text for the element
        List<String> currentData = data.get(position);
        viewHolder.reward_points_id.setText(currentData.get(0));
        viewHolder.reward_points_date.setText(currentData.get(1));
        viewHolder.reward_points_amount.setText(currentData.get(2));
        viewHolder.reward_points_action_type.setText(currentData.get(3));
        viewHolder.reward_points_modified_by.setText(currentData.get(4));
        viewHolder.reward_points_status.setText(currentData.get(5));
        return cell;

    }
}

