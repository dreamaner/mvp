package com.android.mvp.net;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface NetProvider {
    Interceptor[] configInterceptors();

    void configHttps(OkHttpClient.Builder builder);

    CookieJar configCookie();

    RequestHandler configHandler();

    long configConnectTimeoutMills();

    long configReadTimeoutMills();

    boolean configLogEnable();

    boolean handleError(NetError error);
}
