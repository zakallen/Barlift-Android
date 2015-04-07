package com.barliftapp.barlift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FriendAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Object> friends;

    public FriendAdapter(Context c, ArrayList<Object> friends) {
        this.friends = friends;
        mContext = c;
    }

    public int getCount() {
        return friends.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            convertView = inflater.inflate(R.layout.grid_item, null);

            mHolder = new ViewHolder();

            mHolder.textView=(TextView) convertView.findViewById(R.id.tv_friend);
            mHolder.imageView=(ImageView) convertView.findViewById(R.id.iv_friend);


        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        ArrayList<String> friend_detail = (ArrayList<String>)friends.get(position);
        mHolder.textView.setText((friend_detail.get(0)).substring(0, (friend_detail.get(0)).indexOf(" ")));
        Picasso.with(mContext)
                .load("https://graph.facebook.com/" + friend_detail.get(1) + "/picture?type=normal&height=150&width=150")
                .transform(new CircleTransform())
                .into(mHolder.imageView);

        convertView.setTag(mHolder);

        return convertView;
    }
    private class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }
}