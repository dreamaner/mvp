package com.android.mvp.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dreamaner on 2017/5/15.
 */

public class XHeadFootViewHolder extends RecyclerView.ViewHolder {

    public XHeadFootViewHolder(View itemView) {
        super(itemView);
        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        itemView.setTag(this);
    }
}

