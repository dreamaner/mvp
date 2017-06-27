package com.android.kit.view.recycleview.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;



/**
 * Created by Dreamaner on 2017/5/15.
 */

public class SlideInBottomAnimation implements BaseAnimation {



    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
        };
    }
}
