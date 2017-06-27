package com.android.mvp.net;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public interface IModel {
    boolean isNull();       //空数据

    boolean isAuthError();  //验证错误

    boolean isBizError();   //业务错误

    String getErrorMsg();   //后台返回的错误信息
}