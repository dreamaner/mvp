package com.android.mvp.router;

import android.app.Activity;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface RouterCallback {

    void onBefore(Activity from, Class<?> to);

    void onNext(Activity from, Class<?> to);

    void onError(Activity from, Class<?> to, Throwable throwable);

}
