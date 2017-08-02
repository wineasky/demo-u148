package com.example.demo_u148.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.demo_u148.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.HORIZONTAL;

public class TabBar extends HorizontalScrollView{


    public TabBar(Context context) {
        this(context,null);
    }

    public TabBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);

        String[] category_name = getResources().getStringArray(R.array.category_name);
        int len = category_name.length;
        layout.setOrientation(HORIZONTAL);

        for (int i = 0;i < len; i++){
            RelativeLayout itemView = getItemView(context,category_name[i],i);
            layout.addView(itemView);
        }
        addView(layout,lp);

    }

    private RelativeLayout getItemView(Context context, String s, int i) {
        RelativeLayout layout = new RelativeLayout(context);

        TextView tv = new TextView(context);
        tv.setText(s);
        tv.setTextColor(R.color.black);
        tv.setTextSize(15.f);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        lp.setMargins(20,20,20,20);
        lp.addRule(TEXT_ALIGNMENT_CENTER);
        layout.addView(tv,lp);
        return layout;
    }


}
