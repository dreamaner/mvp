package com.android.mvp.cache;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface ICache {
    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();

}
