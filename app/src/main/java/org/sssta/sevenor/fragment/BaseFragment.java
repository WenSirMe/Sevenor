package org.sssta.sevenor.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import org.sssta.sevenor.activity.UserCenterActivity;

/**
 * Created by Heaven on 2015/11/22.
 */
public abstract class BaseFragment extends Fragment {
    public abstract void refresh();
    public abstract void updateContent();
    public void leapUserInfo(Activity activity){
        Intent intent = new Intent(activity, UserCenterActivity.class);
        startActivity(intent);
    }
}
