package com.android.kit.view.progress;

import android.view.Gravity;

import com.android.kit.R;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public class SVProgressHUDAnimateUtil {
    private static final int INVALID = -1;

    static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.TOP:
                return isInAnimation ? R.anim.svslide_in_top : R.anim.svslide_out_top;
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.svslide_in_bottom : R.anim.svslide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.svslide_in_center : R.anim.svslide_out_center;
            default:
                break;
        }
        return INVALID;
    }
}
