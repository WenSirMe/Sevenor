package org.sssta.sevenor.activity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;

import org.sssta.sevenor.R;
import org.sssta.sevenor.fragement.ArticleContentFragment;
import org.sssta.sevenor.fragement.ArticleFragment;
import org.sssta.sevenor.fragement.BaseFragment;
import org.sssta.sevenor.fragement.FindFragment;
import org.sssta.sevenor.fragement.ImageFragment;
import org.sssta.sevenor.util.DensityUtil;
import org.sssta.sevenor.util.MeasureUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener
        , View.OnClickListener
        , ImageFragment.OnFragmentInteractionListener
        , ArticleFragment.OnFragmentInteractionListener
        , FindFragment.OnFragmentInteractionListener
        , ArticleContentFragment.OnFragmentInteractionListener{

    @Bind(R.id.tab_button_image)
    Button tabButtonImage;
    @Bind(R.id.tab_button_article)
    Button tabButtonArticle;
    @Bind(R.id.tab_button_find)
    Button tabButtonFind;
    @Bind(R.id.tab_image_line)
    ImageView tabImageLine;
    @Bind(R.id.main_menu_button)
    ImageButton mainMenuButton;
    private int TAB_CHECK_COLOR;
    private int TAB_UN_CHECK_COLOR;
    private int focusFragmentIndex = 0;
    private ObjectAnimator animator = new ObjectAnimator();
    private BaseFragment[] fragments = new BaseFragment[]{
            ImageFragment.newInstance("", ""),
            ArticleFragment.newInstance("", ""),
            FindFragment.newInstance("", "")
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setSharedElementEnterTransition(new ChangeBounds());
        getWindow().setSharedElementReturnTransition(new ChangeBounds());
        MeasureUtil.setWindowWidth(dm.widthPixels);
        MeasureUtil.setWindowHeight(dm.heightPixels);
        TAB_CHECK_COLOR = getResources().getColor(R.color.tab_text_color_1);
        TAB_UN_CHECK_COLOR = getResources().getColor(R.color.tab_text_color_2);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        tabButtonImage.setTextColor(TAB_CHECK_COLOR);
        tabButtonArticle.setTextColor(TAB_UN_CHECK_COLOR);
        tabButtonFind.setTextColor(TAB_UN_CHECK_COLOR);

        tabButtonImage.setOnTouchListener(this);
        tabButtonArticle.setOnTouchListener(this);
        tabButtonFind.setOnTouchListener(this);

        animator.setTarget(tabImageLine);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setPropertyName("TranslationX");

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragments[0]);
        transaction.commit();

        ImageButton mRefreshButton = new ImageButton(this);
        ImageButton mUserInfoButton  = new ImageButton(this);
        mRefreshButton.setImageDrawable(getDrawable(R.drawable.ic_main_menu_sub_button_back));
        mUserInfoButton.setImageDrawable(getDrawable(R.drawable.ic_main_menu_sub_button_user));
        mRefreshButton.setOnClickListener(this);
        mUserInfoButton.setOnClickListener(this);
        mRefreshButton.setTag(0);
        mUserInfoButton.setTag(1);
        mRefreshButton.setBackgroundColor(Color.argb(0, 0, 0, 0));
        mUserInfoButton.setBackgroundColor(Color.argb(0,0,0,0));
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(mRefreshButton,150,150)
                .addSubActionView( mUserInfoButton,150,150)
                .setStartAngle(0)
                .setEndAngle(180)
                .setRadius(250)
                .attachTo(mainMenuButton)
                .build();


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                switch (v.getId()) {
                    case R.id.tab_button_image:
                        changeState(0);
                        break;
                    case R.id.tab_button_article:
                        changeState(1);
                        break;
                    case R.id.tab_button_find:
                        changeState(2);
                }
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch ((int)v.getTag()){
            case 0:
                fragments[focusFragmentIndex].refresh();
                break;
            case 1:
                fragments[focusFragmentIndex].leapUserInfo(this);
                break;
        }
    }

    private void changeState(int index) {
        int i;
        Button[] views = new Button[]{tabButtonImage, tabButtonArticle, tabButtonFind};
        focusFragmentIndex = index;
        for (i = 0; i < 3; i++) {
            if (i != index) {
                views[i].setTextColor(TAB_UN_CHECK_COLOR);
            }
        }
        views[index].setTextColor(TAB_CHECK_COLOR);

        animator.setFloatValues(
                tabImageLine.getTranslationX()
                , index * DensityUtil.dip2px(this, 120));
        animator.start();

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragments[index]);
        transaction.commit();
    }
}
