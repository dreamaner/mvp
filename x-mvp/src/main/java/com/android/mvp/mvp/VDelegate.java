package com.android.mvp.mvp;

import android.view.View;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface VDelegate {

    void resume();

    void pause();

    void destory();

    void visible(boolean flag, View view);

    void gone(boolean flag, View view);

    void inVisible(View view);

    void toastShort(String msg);

    void toastLong(String msg);

}
