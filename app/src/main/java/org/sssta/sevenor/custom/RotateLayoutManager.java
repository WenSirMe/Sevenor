package org.sssta.sevenor.custom;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import org.sssta.sevenor.R;
import org.sssta.sevenor.util.DensityUtil;
import org.sssta.sevenor.util.MeasureUtil;

import java.util.ArrayList;

/**
 * Created by Heaven on 2015/11/21.
 */
public class RotateLayoutManager implements RotateCardView.RotationChangeListener {
    private ViewGroup mViewGroup;
    private int count = 7;
    private int focusIndex = 0;
    private ArrayList<RotateCardView> views = new ArrayList<>();

    public RotateLayoutManager(ViewGroup mViewGroup) {
        this.mViewGroup = mViewGroup;
        init();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    private void init(){
        createRotateView();
        bindRotateView();
    }
    public void createRotateView(){
        int i;
        for (i=0;i<count;i++){
            final RotateCardView view = (RotateCardView)LayoutInflater.from(mViewGroup.getContext()).inflate(R.layout.item_image_message, mViewGroup, false);
            views.add(view);
            view.setIndex(i);
            view.setOnWindFlagChangeListener(this);
            int width;
            int heigth;
            view.post(new Runnable() {
                @Override
                public void run() {
                    float x = view.getX();
                    float y = view.getY();
                    view.setPivotPoint(
                            DensityUtil.dip2px(mViewGroup.getContext(), 180) - (int) x,
                            DensityUtil.dip2px(mViewGroup.getContext(), 1550) - (int) y);
                }
            });

        }
    }
    public void bindRotateView(){
        int i;
        for (i=0;i<count;i++){
            View child = views.get(i);
            mViewGroup.addView(child);
        }

        updateViewRotate(0,0);
    }
    private void updateViewRotate(float rotation,int index){
        int i;
        for (i=0;i<count;i++){
            if (i == index)
                continue;
            views.get(i).setRotation(rotation + (i - index) * 10);
        }
    }

    @Override
    public void rotationChange(float rotation,int index) {
        updateViewRotate(rotation, index);
        if (views.get(index).getIsDraging())
            boundViewEffect(focusIndex);
        else
            boundViewEffect(index);

    }

    @Override
    public void rotationChangeTouchUp(int focusIndex) {
        this.focusIndex = focusIndex;
    }

    public void boundViewEffect(int index){
        Log.i("mmm",String.valueOf(index));
        int b = index>0?index-1:0;
        int e = index<6?index+1:6;
        int i;
        for (i=b;i<=e;i++){
            views.get(i).setAlpha(1-(float)(0.8*Math.abs(views.get(i).getRotation())/10));
        }
    }
}
