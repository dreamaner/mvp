package com.android.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.kit.view.immersion.ImmersionBar;
import com.android.kit.view.progress.SVProgressHUD;
import com.android.mvp.R;
import com.android.mvp.XDroidConf;
import com.android.mvp.event.BusProvider;
import com.android.mvp.kit.KnifeKit;
import com.android.mvp.kit.StateView;
import com.android.mvp.net.NetError;
import com.android.mvp.recycleview.XRecyclerContentLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxFragment;


import butterknife.Unbinder;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public abstract class XFragment<P extends IPresent> extends RxFragment implements IView<P> {
    public StateView errorView;

    private VDelegate vDelegate;

    private P p;

    protected Activity context;

    private View rootView;

    protected LayoutInflater layoutInflater;

    private RxPermissions rxPermissions;

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public abstract boolean canBack() ;

    @Override
    public void setUpToolBar(boolean able, Toolbar toolbar, String title) {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        bindEvent();
        initData(savedInstanceState);
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    protected VDelegate getvDelegate() {
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
    public void bindEvent() {

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
    public void  showError(XRecyclerContentLayout contentLayout,NetError error){
        if (errorView == null)
            errorView = new StateView(getActivity());
        if (error != null) {
            switch (error.getType()) {
                case NetError.ParseError:
                    errorView.setMsg("数据解析异常");
                    break;

                case NetError.AuthError:
                    errorView.setMsg("身份验证异常");
                    break;

                case NetError.BusinessError:
                    errorView.setMsg("业务异常");
                    break;

                case NetError.NoConnectError:
                    errorView.setMsg("网络无连接");
                    break;

                case NetError.NoDataError:
                    errorView.setMsg("数据为空");
                    break;

                case NetError.OtherError:
                    errorView.setMsg("其他异常");
                    break;
            }
            contentLayout.errorView(errorView);
            contentLayout.showError();
        }
    }
}
