package org.sssta.sevenor.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import org.sssta.sevenor.util.DensityUtil;
import org.sssta.sevenor.util.MathUtil;

/**
 * Created by Heaven on 2015/11/21.
 */
public class RotateCardView extends CardView {
    private boolean isSetPivot = false;
    private float lastRotation;
    private float nrot=0,drot=0;
    private RotationChangeListener mRotationChangeListener;
    private int index = 0;
    private float correctRotation = 0;
    private Boolean isDraging = false;
    private long lastTime = 0;
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public RotateCardView(Context context) {
        super(context);
        setClickable(true);
    }

    public RotateCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }

    public RotateCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float touchX,touchY;
        float downRotation = 0;
        float upRotation = 0;
        int focusIndex = 0;
        final Bundle bundle = new Bundle();
        ObjectAnimator animator = new ObjectAnimator();
        if (animator.isRunning()){
            animator.cancel();
        }

        touchX = ev.getX() - getPivotX();
        touchY = ev.getY() - getPivotY();
        if (!isSetPivot){
            setPivotPoint(getWidth() / 2, getHeight());
            isSetPivot = true;
        }
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("a",String.valueOf(System.currentTimeMillis())+ " "+
                        String.valueOf(System.currentTimeMillis()));
                if (System.currentTimeMillis()-lastTime<300){
                    mRotationChangeListener.doubleClick(index);
                }
                lastTime = System.currentTimeMillis();
                lastRotation = (float)Math.toDegrees(Math.atan2(touchY, touchX));
                downRotation = getRotation();
                break;
            case MotionEvent.ACTION_MOVE:
                nrot = (float)Math.toDegrees(Math.atan2(touchY, touchX));
                drot = nrot - lastRotation;
                setRotation(getRotation() + drot);
                if (mRotationChangeListener!=null){
                    mRotationChangeListener.rotationChange(getRotation(),index);
                }
                break;
            case MotionEvent.ACTION_UP:
                upRotation = getRotation();
                break;
        }
        if (upRotation !=0){
            correctRotation = upRotation-downRotation;
            if ((index == 0 && correctRotation>0) || (index == 6 && correctRotation<0)){
                correctRotation*=-1;
                focusIndex = index;
            }else{
                int t = Math.abs(correctRotation)>5?10:0;
                int k = correctRotation>0?1:-1;
                correctRotation = t*k-correctRotation;
                focusIndex = index-t/10*k;
            }
            mRotationChangeListener.rotationChangeTouchUp(focusIndex);
            Log.i("ff", String.valueOf(index));
            bundle.putInt("i", focusIndex);
            final float beginRotation = getRotation();
            isDraging = true;
            animator.setTarget(this);
            animator.setPropertyName("rotation");
            animator.setInterpolator(new DecelerateInterpolator());
            animator.setFloatValues(getRotation(), getRotation() + correctRotation);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    correctRotation = 0;
                    mRotationChangeListener.rotationChangeTouchUp(bundle.getInt("i"));
                    isDraging = false;
                }
            });
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (mRotationChangeListener!=null){
                        mRotationChangeListener.rotationChange(beginRotation + animation.getAnimatedFraction()*correctRotation,index);
                    }
                }
            });
            animator.start();
        }
        return true;
    }



    public interface RotationChangeListener{
        void rotationChange(float rotation,int index);
        void rotationChangeTouchUp(int focusIndex);
        void doubleClick(int index);
    }
    public void setOnWindFlagChangeListener(RotationChangeListener rotationChangeListener){
        mRotationChangeListener = rotationChangeListener;
    }
    public void setPivotPoint(int x,int y){
        setPivotX(x);
        setPivotY(y);
        isSetPivot = true;
    }

    public Boolean getIsDraging() {
        return isDraging;
    }
}
