package com.android.mvp.mvp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.kit.utils.network.NetworkService;
import com.android.kit.utils.system.AppUtils;
import com.android.kit.utils.ui.AppManagerUitls;
import com.android.kit.view.dialog.DialogAction;
import com.android.kit.view.dialog.GravityEnum;
import com.android.kit.view.dialog.MaterialDialog;
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
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Unbinder;

import static com.android.kit.utils.network.NetworkUtils.NET_BROADCAST_ACTION;
import static com.android.kit.utils.network.NetworkUtils.NET_STATE_NAME;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public abstract class XActivity<P extends IPresent> extends RxAppCompatActivity implements IView<P> {

    private VDelegate vDelegate;
    private P p;
    protected static Activity context;

    private RxPermissions rxPermissions;

    public StateView errorView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;
        AppManagerUitls.getAppManager().addActivity(this);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
            bindEvent();
        }

        initData(savedInstanceState);

    }

    @Override
    public abstract boolean canBack();

    @Override
    public void setUpToolBar(boolean able, Toolbar toolbar, String title) {
        if (able&&toolbar!=null){
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this);
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
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();

    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
        //销毁ImmersionBar
        ImmersionBar.with(this).destroy();
        AppManagerUitls.getAppManager().finishActivity(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
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
    public void bindNetService(){
        context.startService(new Intent(this, NetworkService.class));
    }
    public void unBindNetService(){
        context.stopService(new Intent(this,NetworkService.class));
    }
    @Override
    public void showDialog(String msg) {

        //显示加载框
        SVProgressHUD.showWithStatus(this,msg);
    }

    @Override
    public void hideDialog() {
        //关闭加载框
        SVProgressHUD.dismiss(this);
    }

    @Override
    public void setImmersionBar(int color) {
        //设置沉浸状态栏的颜色
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .barColor(color)
                .init();
    }

    @Override
    public void initImmersionBar() {

        //初始化
        ImmersionBar.with(this).init();
    }

    public void initImmersionBarWithFullScreen() {
        //全屏显示图片
        ImmersionBar.with(this).transparentBar().init();
    }
    public void  showError(XRecyclerContentLayout contentLayout,NetError error){
        if (errorView == null){
            errorView = new StateView(this);
        }
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

    public int showState(int state){
        switch (state){
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
        return state;
    }
}
