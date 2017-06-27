package com.android.xmvp.ui;

import android.os.Bundle;

import android.support.annotation.NonNull;
import com.android.kit.view.dialog.DialogAction;
import com.android.kit.view.dialog.MaterialDialog;
import com.android.mvp.base.SimpleRecAdapter;
import com.android.mvp.event.BusProvider;
import com.android.mvp.kit.StateEvent;
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
        load();
    }
    public void load(){
        BusProvider.getBus().toFlowable(StateEvent.class)
            .subscribe(new Consumer<StateEvent>() {
                @Override
                public void accept(StateEvent stateEvent) throws Exception {
                    switch (stateEvent.getState()){
                        case -1:
                            new MaterialDialog.Builder(context)
                                .title("提示")
                                .content("网络已经断开")
                                .canceledOnTouchOutside(false)
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                            break;
                        case 1:
                            getP().loadData(getType(), 1);
                            break;
                        case 2:
                            new MaterialDialog.Builder(context)
                                .title("提示")
                                .content("当前网络为2G,加载可能有点慢")
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                            break;
                    }
                }
            });
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



//        contentLayout.loadingView(View.inflate(getContext(), R.layout.loading_view, null));

        contentLayout.getRecyclerView().useDefLoadMoreView();
    }

    @Override
    public boolean useEventBus() {
        return true;
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


}
