package com.android.xmvp.ui;

import android.support.v7.widget.Toolbar;

import com.android.mvp.base.SimpleRecAdapter;
import com.android.mvp.recycleview.RecyclerItemCallback;
import com.android.mvp.recycleview.XRecyclerView;
import com.android.xmvp.adapter.HomeAdapter;
import com.android.xmvp.model.GankResults;



/**
 * Created by Dreamaner on 2017/5/15.
 */

public class HomeFragment extends BasePagerFragment {

    HomeAdapter adapter;

    @Override
    public SimpleRecAdapter getAdapter() {
        if (adapter == null) {
            adapter = new HomeAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GankResults.Item, HomeAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, GankResults.Item model, int tag, HomeAdapter.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                    switch (tag) {
                        case HomeAdapter.TAG_VIEW:
                            WebActivity.launch(context, model.getUrl(), model.getDesc());
                            break;
                    }
                }
            });
        }
        return adapter;
    }

    @Override
    public void setLayoutManager(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(context);
    }

    @Override
    public String getType() {
        return "all";
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public boolean canBack() {
        return false;
    }

}
