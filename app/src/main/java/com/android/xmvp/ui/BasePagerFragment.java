package com.android.xmvp.ui;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.kit.utils.toast.ToastUtils;
import com.android.mvp.base.SimpleRecAdapter;

import com.android.mvp.mvp.XLazyFragment;
import com.android.mvp.net.NetError;
import com.android.mvp.recycleview.XRecyclerContentLayout;
import com.android.mvp.recycleview.XRecyclerView;
import com.android.xmvp.R;
import com.android.xmvp.present.PBasePager;

import com.android.xmvp.model.GankResults;



import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public abstract class BasePagerFragment extends XLazyFragment<PBasePager> {

    @BindView(R.id.contentLayout)
    XRecyclerContentLayout contentLayout;

    protected static final int MAX_PAGE = 10;

    @Override
    public void initData(Bundle savedInstanceState) {
        initAdapter();
        getP().loadData(getType(), 1);
        setImmersionBar(R.color.colorPrimaryDark);
    }

    private void initAdapter() {

        setLayoutManager(contentLayout.getRecyclerView());
        contentLayout.getRecyclerView()
                .setAdapter(getAdapter());
        contentLayout.getRecyclerView()
                .setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        getP().loadData(getType(), 1);
                    }

                    @Override
                    public void onLoadMore(int page) {
                        getP().loadData(getType(), page);
                    }
                });

        contentLayout.getRecyclerView().useDefLoadMoreView();
    }

    public abstract SimpleRecAdapter getAdapter();

    public abstract void setLayoutManager(XRecyclerView recyclerView);

    public abstract String getType();

    public void showError(NetError error){
        showError(contentLayout,error);
    }

    public void showData(int page, GankResults model) {
        if (page > 1) {
            getAdapter().addData(model.getResults());
        } else {
            getAdapter().setData(model.getResults());
        }

        contentLayout.getRecyclerView().setPage(page, MAX_PAGE);

        if (getAdapter().getItemCount() < 1) {
            contentLayout.showEmpty();
            return;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_pager;
    }

    @Override
    public PBasePager newP() {
        return new PBasePager();
    }

    @Override
    public void onStateViewClick() {

        contentLayout.showLoading();
        getP().loadData(getType(), 1);

    }

}
