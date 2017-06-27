package com.android.xmvp;

import android.app.Application;
import android.content.Context;


import com.android.kit.utils.system.AppUtils;
import com.android.mvp.AppInit;
import com.android.mvp.net.NetError;
import com.android.mvp.net.NetProvider;
import com.android.mvp.net.RequestHandler;
import com.android.mvp.net.XApi;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
//        DebugDB.getAddressLog();
        AppUtils.init(this);
        AppInit.parmConfig(true,true,"","","XDroid");

        XApi.registerProvider(new NetProvider() {

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }
        });
    }

    public static Context getContext() {
        return context;
    }
}
