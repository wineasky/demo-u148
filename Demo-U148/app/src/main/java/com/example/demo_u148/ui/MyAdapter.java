package com.example.demo_u148.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.demo_u148.R;
import com.example.demo_u148.feed.Feed;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private ArrayList<Feed> mFeed = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item,parent,false);
        MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mFeed.size() == 0){
            holder.title.setText("什么也没有");
        }else {
            Feed feed = mFeed.get(position);
            holder.title.setText(feed.title);
            holder.pic.setImageBitmap(feed.bmp_pic);
        }
    }

    @Override
    public int getItemCount() {
        if (mFeed.size() != 0) return mFeed.size();
        return 1;
    }

    public void addData(ArrayList<Feed> feedList) {
        mFeed.clear();
        if (feedList != null){
            mFeed.addAll(feedList);
        }else {

        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tv_title);
            pic = (ImageView)itemView.findViewById(R.id.pic);
        }
    }



}
