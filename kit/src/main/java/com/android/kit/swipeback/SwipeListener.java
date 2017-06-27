package com.android.kit.swipeback;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface SwipeListener {
        void onScroll(float percent, int px);
        void onEdgeTouch();
        /**
         * Invoke when scroll percent over the threshold for the first time
         */
        void onScrollToClose();
    }