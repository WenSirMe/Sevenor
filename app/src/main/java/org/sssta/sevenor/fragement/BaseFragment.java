package org.sssta.sevenor.fragement;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Heaven on 2015/11/22.
 */
public abstract class BaseFragment extends Fragment {
    public abstract void refresh();
    public abstract void updateContent();
    public void leapUserInfo(Activity activity){

    }
}
