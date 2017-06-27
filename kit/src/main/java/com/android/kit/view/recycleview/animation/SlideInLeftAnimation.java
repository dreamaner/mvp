package com.android.kit.view.recycleview.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;



/**
 * Created by Dreamaner on 2017/5/15.
 */

public class SlideInLeftAnimation implements BaseAnimation {


  @Override
  public Animator[] getAnimators(View view) {
    return new Animator[] {
        ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0)
    };
  }
}
