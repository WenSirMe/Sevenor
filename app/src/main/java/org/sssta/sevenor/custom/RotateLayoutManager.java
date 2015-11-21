package org.sssta.sevenor.custom;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.sssta.sevenor.R;
import org.sssta.sevenor.activity.ImageReadActivity;
import org.sssta.sevenor.model.Message;
import org.sssta.sevenor.util.DensityUtil;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Heaven on 2015/11/21.
 */
public class RotateLayoutManager implements RotateCardView.RotationChangeListener {

    private ViewGroup mViewGroup;
    private int count = 7;
    private int focusIndex = 0;
    private static final int beginIndex = 3;
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private ArrayList<RotateCardView> views = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    private Activity mActivity;
    public RotateLayoutManager(ViewGroup mViewGroup,Activity activity) {
        this.mViewGroup = mViewGroup;
        mActivity = activity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addRotateView() {

        if (views.isEmpty() && mViewGroup.getChildCount() == 0) {
            createRotateView();
            addInRotateView();

        }

    }

    public void createRotateView() {
        int i;
        for (i = 0; i < count; i++) {
            final RotateCardView view = (RotateCardView) LayoutInflater.from(mViewGroup.getContext()).inflate(R.layout.item_image_message, mViewGroup, false);
            views.add(view);
            view.setIndex(i);
            view.setOnWindFlagChangeListener(this);
            ((ViewGroup)view).setTransitionGroup(false);
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

    private void addInRotateView() {
        int i;
        for (i = 0; i < count; i++) {
            View child = views.get(i);
            mViewGroup.addView(child, i);
        }
    }

    public void bindRotateView() {
        int i;
        for (i = 0; i < count; i++) {
            final ImageView imageView = (ImageView)views.get(i).findViewById(R.id.image_card);
            imageView.setImageResource(messages.get(i).getImageIndex());
            imageViews.add(imageView);
        }
        updateViewRotate(0,beginIndex);
        boundViewEffect(beginIndex);
    }

    private void updateViewRotate(float rotation, int index) {
        int i;
        for (i = 0; i < count; i++) {
            if (i == index)
                continue;
            views.get(i).setRotation(rotation + (i - index) * 10);
        }
    }

    @Override
    public void rotationChange(float rotation, int index) {
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

    public void boundViewEffect(int index) {
        int i;
        for (i = Math.max(index - 2, 0); i <= Math.min(index + 2, 6); i++) {
            views.get(i).setAlpha(1 - (float) (0.8 * Math.abs(views.get(i).getRotation()) / 10));
            views.get(i).setScaleX(1 - (float) (0.2 * Math.abs(views.get(i).getRotation()) / 10));
            views.get(i).setScaleY(1 - (float) (0.2 * Math.abs(views.get(i).getRotation()) / 10));
        }
    }

    public void clearView() {
        int i;
        for (i = 0; i < 7; i++)
            views.get(i).setVisibility(View.GONE);
    }

    public void clearContent() {
        views.clear();
    }

    public void updateViewContent(ArrayList<Message> list) {
        messages = list;
        bindRotateView();
    }

    @Override
    public void doubleClick(int index) {
        Intent intent = new Intent(mActivity, ImageReadActivity.class);
        intent.putExtra("resId", messages.get(index).getImageIndex());
        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(mActivity, imageViews.get(index), "rob");
        mActivity.startActivity(intent, options.toBundle());
    }
}
