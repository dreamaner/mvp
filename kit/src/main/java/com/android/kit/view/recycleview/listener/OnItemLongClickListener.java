package com.android.kit.view.recycleview.listener;

import android.view.View;

import com.android.kit.view.recycleview.BaseQuickAdapter;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public abstract class OnItemLongClickListener extends SimpleClickListener {




    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemLongClick( adapter,  view,  position);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
    public abstract void onSimpleItemLongClick(BaseQuickAdapter adapter, View view, int position);
}
