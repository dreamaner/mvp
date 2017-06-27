package com.android.mvp.mvp;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface IPresent<V> {

    void attachV(V view);

    void detachV();

}
