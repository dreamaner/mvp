package com.android.mvp.kit;

import com.android.mvp.event.IBus;

/**
 * Author: yury
 * Created on: 2017/6/27 17:47
 * Description:网络状态事件
 */
public class StateEvent implements IBus.IEvent {
    public int state;

    public StateEvent(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int getTag() {
        return 1;
    }
}
