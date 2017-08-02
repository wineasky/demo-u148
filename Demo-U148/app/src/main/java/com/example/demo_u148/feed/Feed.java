package com.example.demo_u148.feed;

import android.graphics.Bitmap;

public class Feed{
    public String id;
    public String uid;
    public int category;
    public String title;
    public String summary;
    public String tids;
    public String tags;
    public String pic_min;
    public String pic_mid;
    public int is_index;
    public int is_hot;
    public int is_top;
    public int star;
    public int count_browse;
    public int count_review;
    public long create_time;
    public long update_time;
    public UserInfo usr;
    public Bitmap bmp_pic;
    public String type;

    public static class UserInfo {
        public String nickname;
        public String alias;
        public String icon;
    }
}
