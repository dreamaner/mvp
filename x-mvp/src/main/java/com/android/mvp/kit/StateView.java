package com.android.mvp.kit;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.android.mvp.R;

/**
 * Created by Dreamaner on 2017/5/15.
 */
public class StateView extends LinearLayout {


    TextView Msg;

    public StateView(Context context) {
        super(context);
        setupView(context);
    }

    public StateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public StateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    private void setupView(Context context) {
      View view =  inflate(context, R.layout.view_state, this);
      Msg = (TextView)view.findViewById(R.id.msg);
    }

    public void setMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Msg.setText(msg);
        }
    }
}
