package com.android.mvp.router;

import android.app.Activity;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public class SimpleRouterCallback implements RouterCallback {

    @Override
    public void onBefore(Activity from, Class<?> to) {

    }

    @Override
    public void onNext(Activity from, Class<?> to) {

    }

    @Override
    public void onError(Activity from, Class<?> to, Throwable throwable) {

    }
}
