package com.example.demo_u148;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import com.example.demo_u148.feed.Feed;
import com.example.demo_u148.ui.MyAdapter;
import com.example.demo_u148.ui.TabBar;
import com.google.gson.*;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private final static String REQUEST_URL = "http://api.u148.net/json/0/1";
    private final static int MSG_REQUEST_OK = 233;

    private MyAdapter mAdapter;
    private ArrayList<Feed> feedList;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;
    private TabBar bar;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (MSG_REQUEST_OK != msg.what) return;
            mAdapter.addData(feedList);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handling();
        initView();
    }

    private  void initView(){
        bar = (TabBar)findViewById(R.id.bar);
        setRecyclerView();
    }


    private void setRecyclerView(){
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        layoutManager = new StaggeredGridLayoutManager(2,VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);

    }

    public void handling(){

        new Thread() {
            @Override
            public void run() {
                feedList = readFeed();
                if (feedList != null) {
                    mHandler.sendEmptyMessage(MSG_REQUEST_OK);
                }
            }
        }.start();

    }



    private ArrayList<Feed> readFeed(){
        String jsonData = getJsonData();
        if( jsonData != null) {
            JsonParser parser = new JsonParser();
            Gson gson = new Gson();
            feedList = new ArrayList<>();

            JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();
            JsonObject data = jsonObject.getAsJsonObject("data");
            JsonArray feedData = data.getAsJsonArray("data");

            for (JsonElement feeds : feedData) {
                Feed feed = gson.fromJson(feeds, Feed.class);
                feedList.add(feed);
                Log.i("feed", "作者：" + feed.usr.nickname);
                feed.bmp_pic = getBitmap(feed.pic_min);
            }
            return feedList;
        }
        return null;
    }

    public String getJsonData() {
        try {
            Response response = setCall(REQUEST_URL).execute();
            String responseData = response.body().string();
            return responseData;
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("提示:","未能获取到json");
            return null;
        }
    }

    public Call setCall(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        return call;
    }

    public Bitmap getBitmap(String img_url) {
        Call call = setCall(img_url);

        try {
            Response response = call.execute();
            byte[] bytes = response.body().bytes();
            return  BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}