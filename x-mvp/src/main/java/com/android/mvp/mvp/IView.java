package com.android.mvp.mvp;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface IView<P> {

    void bindUI(View rootView);

    void bindEvent();

    void initData(Bundle savedInstanceState);

    int getOptionsMenuId();

    int getLayoutId();

    boolean useEventBus();

    void showDialog(String msg);

    void hideDialog();

    void setImmersionBar(int color);

    P newP();

    void initImmersionBar();

    boolean canBack();

    void setUpToolBar(boolean able,Toolbar toolbar, String title);
}

