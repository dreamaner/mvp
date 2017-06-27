package com.android.xmvp.present;


import com.android.mvp.mvp.XPresent;
import com.android.mvp.net.ApiSubscriber;
import com.android.mvp.net.NetError;
import com.android.mvp.net.XApi;
import com.android.xmvp.model.GankResults;
import com.android.xmvp.net.Api;
import com.android.xmvp.ui.BasePagerFragment;


/**
 * Created by dreamaner on 2017/5/15.
 */

public class PBasePager extends XPresent<BasePagerFragment> {
    protected static final int PAGE_SIZE = 10;


    public void loadData(String type, final int page) {
        Api.getGankService().getGankData(type, PAGE_SIZE, page)
                .compose(XApi.<GankResults>getApiTransformer())
                .compose(XApi.<GankResults>getScheduler())
                .compose(getV().<GankResults>bindToLifecycle())
                .subscribe(new ApiSubscriber<GankResults>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);

                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
//                        getV().showDialog("请求中...");
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
//                        getV().hideDialog();
                    }

                    @Override
                    public void onNext(GankResults gankResults) {
                        getV().showData(page, gankResults);
                    }
                });
    }
}
