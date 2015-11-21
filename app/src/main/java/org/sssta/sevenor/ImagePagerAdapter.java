package org.sssta.sevenor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.sssta.sevenor.fragement.ImageFragment;

/**
 * Created by Heaven on 2015/11/20.
 */
public class ImagePagerAdapter extends FragmentPagerAdapter {
    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance("","");
    }

    @Override
    public int getCount() {
        return 7;
    }
}
