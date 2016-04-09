package com.sweedelight.www.sweedelight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




public class AccountDashboardListAdapter extends BaseAdapter {
    private Context context;


    public static class ViewHolder {
        TextView textView;
        ImageView imageView;
        int position;
    }

    // references to icons
    private Integer[] images;

    private String[] labels;

    public AccountDashboardListAdapter(Context c, Integer[] images, String[] labels) {
        context = c;

        // list element image id's
        this.images = images;

        // List element labels
        this.labels = labels;
    }

    public int getCount() {
        return images.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View cell = convertView;
        if (cell == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.account_list_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) cell.findViewById(R.id.list_image);
            viewHolder.textView = (TextView) cell.findViewById(R.id.list_text);
            viewHolder.position = position;
            cell.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) cell.getTag();
        }

        // set the Image for the element
        loadBitmap(images[position],viewHolder.imageView);                 //for efficiency and smoother list view scrolling
        viewHolder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //viewHolder.imageView.setAdjustViewBounds(true);
        viewHolder.imageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));

        // set the Text for the element
        viewHolder.textView.setText(labels[position]);
        return cell;
    }

    // loads the icon onto the element asynchronously
    public void loadBitmap(int resId, ImageView imageView) {
        AsyncImageLoaderHelper helper = new AsyncImageLoaderHelper(context);
        helper.loadBitmap(resId,imageView);
    }



}