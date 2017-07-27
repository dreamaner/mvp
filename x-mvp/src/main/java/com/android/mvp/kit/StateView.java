package com.android.mvp.kit;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mvp.R;




import butterknife.BindView;


/**
 * Created by Dreamaner on 2017/5/15.
 */
public class StateView extends LinearLayout {

    LinearLayout state;
    TextView tvMsg;

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
        tvMsg = (TextView)view.findViewById(R.id.msg);
        state = (LinearLayout)view.findViewById(R.id.state);
        state.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onStateViewClick();
            }
        });

    }

    public void setMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
    }

    //这里，我们定义一个接口
    public interface OnStateViewClickListener {
         void onStateViewClick();
    }

    private OnStateViewClickListener mListener;

    //写一个设置接口监听的方法
    public void setOnItemClickListener(OnStateViewClickListener listener) {
        mListener = listener;
    }
}
