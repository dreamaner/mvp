package com.android.mvp.recycleview;

/**
 * Created by dreamaner on 2017/5/15.
 */

public interface LoadMoreUIHandler {

    void onLoading();

    void onLoadFinish(boolean hasMore);
}
