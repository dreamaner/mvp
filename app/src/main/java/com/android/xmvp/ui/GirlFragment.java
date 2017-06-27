package com.android.xmvp.ui;


import android.support.v7.widget.Toolbar;

import com.android.mvp.base.SimpleRecAdapter;
import com.android.mvp.recycleview.RecyclerItemCallback;
import com.android.mvp.recycleview.XRecyclerView;
import com.android.xmvp.adapter.GirlAdapter;
import com.android.xmvp.model.GankResults;



/**
 * Created by Dreamaner on 2017/5/15.
 */

public class GirlFragment extends BasePagerFragment {

    GirlAdapter adapter;

    @Override
    public SimpleRecAdapter getAdapter() {
        if (adapter == null) {
            adapter = new GirlAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GankResults.Item, GirlAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, GankResults.Item model, int tag, GirlAdapter.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                }
            });
        }
        return adapter;
    }

    @Override
    public void setLayoutManager(XRecyclerView recyclerView) {
        recyclerView.gridLayoutManager(context, 2);

    }

    @Override
    public String getType() {
        return "福利";
    }

    public static GirlFragment newInstance() {
        return new GirlFragment();
    }


    @Override
    public boolean canBack() {
        return false;
    }

}
