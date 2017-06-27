package com.android.mvp.net;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface RequestHandler {
    Request onBeforeRequest(Request request, Interceptor.Chain chain);

    Response onAfterRequest(Response response, String result, Interceptor.Chain chain);
}
