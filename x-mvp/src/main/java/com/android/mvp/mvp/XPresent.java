package com.android.mvp.mvp;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public class XPresent<V extends IView> implements IPresent<V> {

    private V v;

    @Override
    public void attachV(V view) {
        v = view;
    }

    @Override
    public void detachV() {
        v = null;
    }

    protected V getV() {
        if (v == null) {
            throw new IllegalStateException("v can not be null");
        }
        return v;
    }

}
