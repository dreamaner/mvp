package com.android.mvp.event;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface IBus {

    void register(Object object);
    void unregister(Object object);
    void post(IEvent event);
    void postSticky(IEvent event);


    interface IEvent {
        int getTag();
    }
}
