package com.android.xmvp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.solver.Cache;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.kit.utils.cache.CacheUtils;
import com.android.kit.utils.system.AppUtils;
import com.android.kit.view.immersion.ImmersionBar;
import com.android.mvp.mvp.XActivity;
import com.android.mvp.router.Router;
import com.android.xmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public class AboutActivity extends XActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //@BindView(R.id.appbar)
    //AppBarLayout appbar;
    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    public void initData(Bundle savedInstanceState) {

        setUpToolBar(true, toolbar, "关于");
        //setImmersionBar(R.color.md_red_A700);
        //initImmersionBar();
        //toolbar.setBackgroundResource(R.color.black);
        try {
            tvInfo.setText(CacheUtils.getTotalCacheSize(AppUtils.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launch(Activity activity) {
        Router.newIntent(activity)
                .to(AboutActivity.class)
                .data(new Bundle())
                .launch();

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public Object newP() {
        return null;
    }


    @Override
    public boolean canBack() {
        return true;
    }


    @OnClick(R.id.tv_info)
    public void onViewClicked() {
        CacheUtils.clearAllCache(this);
        try {
            tvInfo.setText(CacheUtils.getTotalCacheSize(AppUtils.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
