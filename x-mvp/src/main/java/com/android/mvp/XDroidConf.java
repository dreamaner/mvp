package com.android.mvp;


import com.android.mvp.router.Router;
import com.android.mvp.imageloader.ILoader;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public class XDroidConf {

    // #log
    public static final boolean LOG = AppInit.list.get(0).isLOG();
    public static final String LOG_TAG = AppInit.list.get(0).LOG_TAG;
    // #cache
    public static final String CACHE_SP_NAME = AppInit.list.get(0).getCACHE_SP_NAME();
    public static final String CACHE_DISK_DIR = AppInit.list.get(0).getCACHE_DISK_DIR();
    // #router
    public static final int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static final int ROUTER_ANIM_EXIT = Router.RES_NONE;

    // #imageloader
    public static final int IL_LOADING_RES = ILoader.Options.RES_NONE;
    public static final int IL_ERROR_RES = ILoader.Options.RES_NONE;

    // #dev model
    public static final boolean DEV = AppInit.list.get(0).isDEV();
}
