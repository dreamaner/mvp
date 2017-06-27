package com.android.kit.view.dialog.color;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * Created by Dreamaner on 2017/5/15.
 */

public class FillGridView extends GridView {

  public FillGridView(Context context) {
    super(context);
  }

  public FillGridView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FillGridView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
  }
}
