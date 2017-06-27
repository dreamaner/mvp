package com.android.mvp;

import android.app.Application;
import android.content.Context;

import com.android.kit.utils.system.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public class AppInit extends Application {

    public static List<XDroidConfig> list;

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        parmConfig(true,true,"xm","XDroid","XDroid");

    }

    //全局配置log,缓存,开发模式的使用与否
    public static void parmConfig(boolean isLog,
                                  boolean isDev,
                                  String cache_sp_name,
                                  String cache_disk_dir,
                                  String log_tag){

        list = new ArrayList<XDroidConfig>();

        XDroidConfig config = new XDroidConfig();
                     config.setLOG(isLog);
                     config.setDEV(isDev);
                     config.setCACHE_SP_NAME(cache_sp_name);
                     config.setCACHE_DISK_DIR(cache_disk_dir);
                     config.setLOG_TAG(log_tag);
        list.add(config);
    }
}
