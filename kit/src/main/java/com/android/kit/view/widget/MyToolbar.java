package com.android.kit.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.android.kit.R;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public class MyToolbar extends Toolbar {

//    private SkinCompatBackgroundHelper backgroundHelper;
    private boolean isShow=true;

    public MyToolbar(Context context) {
        this(context,null);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }


    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        backgroundHelper=new SkinCompatBackgroundHelper(this);
//        backgroundHelper.loadFromAttributes(attrs,defStyleAttr);
    }

    public void hide(){
        this.animate()
                .translationY(-this.getHeight())
                .setInterpolator(new AccelerateInterpolator(2));
        isShow=false;
    }

    public void show(){
        this.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(2));
        isShow=true;
    }

    public boolean isShow() {
        return isShow;
    }

//    @Override
//    public void applySkin() {
//        if (backgroundHelper!=null)
//            backgroundHelper.applySkin();
//    }
}
