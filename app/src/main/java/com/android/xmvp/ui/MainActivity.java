package com.android.xmvp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.kit.view.tab.XTabLayout;
import com.android.mvp.base.XFragmentAdapter;
import com.android.mvp.log.XLog;
import com.android.mvp.mvp.XActivity;
import com.android.xmvp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public class MainActivity extends XActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    XTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"首页", "干货", "妹子"};

    XFragmentAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        setUpToolBar(true,toolbar, "mvp");
//        setSupportActionBar(toolbar);
//        setImmersionBar(R.color.colorPrimary);
//        initImmersionBar();
        bindNetService();
        XLog.i("---","服务开启");
        fragmentList.clear();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(GanhuoFragment.newInstance());
        fragmentList.add(GirlFragment.newInstance());

        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getOptionsMenuId() {
        return R.menu.menu_main;
    }

    @Override
    public boolean canBack() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_droid:
                AboutActivity.launch(context);
                XLog.i("---","服务暂停");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Object newP() {
        return null;
    }

}
