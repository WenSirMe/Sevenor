package org.sssta.sevenor.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.sssta.sevenor.util.DensityUtil;

/**
 * Created by Heaven on 2015/11/20.
 */
public class RotateViewPager extends ViewPager {
    private Context context;
    private float rotateIndex = 0;

    public RotateViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public RotateViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        final int count = getChildCount();
        int width = r - l;
        int height = b - t;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        final int scrollX = getScrollX();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            child.layout(paddingLeft, paddingTop,
                    paddingLeft + child.getMeasuredWidth(),
                    paddingTop + child.getMeasuredHeight());
            child.setPivotX(DensityUtil.dip2px(context, 180));
            child.setPivotY(DensityUtil.dip2px(context, 1450));
            child.setRotation((i + rotateIndex) * 10);
            Log.i("index",String.valueOf(rotateIndex));
        }
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        if (position>0 && position <7){
            final View left = getChildAt(position-1);
            final View now = getChildAt(position);
            final View right = getChildAt(position+1);
            left.setRotation((offset-1)*10);
            now.setRotation(offset*10);
           // right.setRotation((offset+1)*10);
        }
    }
}
