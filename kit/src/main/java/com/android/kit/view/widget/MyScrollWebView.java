package com.android.kit.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.android.kit.utils.log.LogUtils;


/**
 * Created by Dreamaner on 2017/5/15.
 */


public class MyScrollWebView extends WebView{


    private static final String TAG = "MyScrollWebView";

    private MyScrollListener scrollListener;


    public void setScrollListener(MyScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public MyScrollWebView(Context context) {
        super(context);
    }


    public MyScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int dy=t-oldt;

//        LogUtils.d("l : ",l);
//        LogUtil.d("t : ",t);
//        LogUtil.d("oldl : ",oldl);
//        LogUtil.d("oldt : ",oldt);

        if (scrollListener!=null)
            scrollListener.onScroll(dy);
    }

    public  interface  MyScrollListener{
         void onScroll(int dy);
    }



}
