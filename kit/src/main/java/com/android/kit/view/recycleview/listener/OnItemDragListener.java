package com.android.kit.view.recycleview.listener;

import android.support.v7.widget.RecyclerView;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface OnItemDragListener {
    void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos);
    void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to);
    void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos);

}
