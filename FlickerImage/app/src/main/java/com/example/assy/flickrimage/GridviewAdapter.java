package com.example.assy.flickrimage;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GridviewAdapter extends BaseAdapter{

    private Context context;
    private String[] items;


    public GridviewAdapter(Context context, String[] items){
        super();
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.length;
    }


    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView img = null;
        if (convertView == null) {

            img = new ImageView(context);

            convertView = img;
            img.setPadding(10, 10, 10, 10);
            img.setAdjustViewBounds(true);

        } else {
            img = (ImageView) convertView;
        }



        Picasso.with(context)

                .load(items[position])

                .placeholder(R.drawable.picture)
                .resize(800, 1600)

                .into(img);

        return convertView;
    }

}