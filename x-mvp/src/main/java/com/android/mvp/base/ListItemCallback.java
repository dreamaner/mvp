package com.android.mvp.base;

/**
 * Created by Dreamaner on 2017/5/15.
 */


public abstract class ListItemCallback<T> {

    public void onItemClick(int position, T model, int tag) {}

    public void onItemLongClick(int position, T model, int tag) {}
}
