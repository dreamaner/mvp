package com.android.xmvp.ui;


import android.support.v7.widget.Toolbar;

import com.android.mvp.base.SimpleRecAdapter;
import com.android.mvp.recycleview.RecyclerItemCallback;
import com.android.mvp.recycleview.XRecyclerView;
import com.android.xmvp.adapter.GanhuoAdapter;
import com.android.xmvp.model.GankResults;



public class GanhuoFragment extends BasePagerFragment {

    GanhuoAdapter adapter;

    @Override
    public SimpleRecAdapter getAdapter() {
        if (adapter == null) {
            adapter = new GanhuoAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GankResults.Item, GanhuoAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, GankResults.Item model, int tag, GanhuoAdapter.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                    switch (tag) {
                        case GanhuoAdapter.TAG_VIEW:
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
        return "Android";
    }

    public static GanhuoFragment newInstance() {
        return new GanhuoFragment();
    }

    @Override
    public boolean canBack() {
        return false;
    }

}
