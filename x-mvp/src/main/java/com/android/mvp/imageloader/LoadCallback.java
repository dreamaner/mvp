package com.android.mvp.imageloader;

import android.graphics.Bitmap;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public abstract class LoadCallback {
    void onLoadFailed(Throwable e) {}

    public abstract void onLoadReady(Bitmap bitmap);

    void onLoadCanceled() {}
}
