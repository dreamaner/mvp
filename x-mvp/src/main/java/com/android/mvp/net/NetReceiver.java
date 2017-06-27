package com.android.mvp.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.android.kit.utils.system.AppUtils;
import com.android.kit.utils.toast.ToastUtils;
import com.android.kit.view.dialog.DialogAction;
import com.android.kit.view.dialog.MaterialDialog;
import com.android.mvp.event.BusProvider;
import com.android.mvp.kit.StateEvent;
import com.android.mvp.log.XLog;
import com.android.mvp.mvp.XActivity;
import com.android.mvp.mvp.XFragment;
import com.android.mvp.mvp.XLazyFragment;

import static com.android.kit.utils.network.NetworkUtils.NET_STATE_NAME;

/**
 * Author: dreamaner
 * Created on: 2017/6/27 14:10
 * Description:网络状态广播接收处理
 */
public class NetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getExtras().getInt(NET_STATE_NAME);
        XLog.i("---",state+"");
        BusProvider.getBus().post(new StateEvent(state));
    }
}
