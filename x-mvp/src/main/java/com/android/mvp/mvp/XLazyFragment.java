package com.android.mvp.mvp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.kit.utils.toast.ToastUtils;
import com.android.kit.view.immersion.ImmersionBar;
import com.android.kit.view.progress.SVProgressHUD;
import com.android.mvp.R;
import com.android.mvp.XDroidConf;
import com.android.mvp.event.BusProvider;
import com.android.mvp.kit.KnifeKit;
import com.android.mvp.kit.StateView;
import com.android.mvp.log.XLog;
import com.android.mvp.net.NetError;
import com.android.mvp.recycleview.XRecyclerContentLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.Unbinder;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public abstract class XLazyFragment<P extends IPresent>
        extends LazyFragment implements IView<P>,StateView.OnStateViewClickListener {

    public StateView errorView;

    private VDelegate vDelegate;

    private P p;

    private RxPermissions rxPermissions;

    private Unbinder unbinder;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(getRealRootView());
        }
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        bindEvent();
        initData(savedInstanceState);
        errorView = new StateView(getActivity());
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    @Override
    public void bindEvent() {

    }
    @Override
    public abstract boolean canBack();
    @Override
    public  void setUpToolBar(boolean able, Toolbar toolbar, String title){
        if (able&&toolbar!=null){
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            if (canBack()){
                if (actionBar != null)
                    actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);

            }
            actionBar.setTitle(title);
        }
    }
    public VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }
    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }
    @Override
    protected void onDestoryLazy() {
        super.onDestoryLazy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();

        p = null;
        vDelegate = null;
    }
    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(getActivity());
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }
    @Override
    public int getOptionsMenuId() {
        return 0;
    }
    @Override
    public boolean useEventBus() {
        return false;
    }
    @Override
    public void showDialog(String msg) {

        //显示加载框
        SVProgressHUD.showWithStatus(getContext(),msg);
    }
    @Override
    public void hideDialog() {
        //关闭加载框
        SVProgressHUD.dismiss(getContext());
    }
    @Override
    public void setImmersionBar(int color) {
        //设置沉浸状态栏的颜色
        ImmersionBar.with(getActivity())
                .fitsSystemWindows(true)
                .barColor(color)
                .init();
    }
    @Override
    public void initImmersionBar() {
        //初始化状态栏
        ImmersionBar.with(getActivity()).init();
    }
    @Override
    public void  showError(XRecyclerContentLayout contentLayout,NetError error){

        if (error != null) {

            switch (error.getType()) {
                case NetError.ParseError:
                    errorView.setMsg("数据解析异常,点击重试");
                    break;

                case NetError.AuthError:
                    errorView.setMsg("身份验证异常,点击重试");
                    break;

                case NetError.BusinessError:
                    errorView.setMsg("业务异常,点击重试");
                    break;

                case NetError.NoConnectError:
                    errorView.setMsg("网络异常,点击重试");
                    break;

                case NetError.NoDataError:
                    errorView.setMsg("数据为空,点击重试");
                    break;

                case NetError.OtherError:
                    errorView.setMsg("其他异常,点击重试");
                    break;
            }
            errorView.setOnItemClickListener(this);
            contentLayout.errorView(errorView).showError();
        }

    }
    @Override
    public void onStateViewClick() {
        //网络状态回调监听

    }
}
