package org.sssta.sevenor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.sssta.sevenor.fragment.ArticleContentFragment;

/**
 * Created by Heaven on 2015/11/22.
 */
public class ArticleAdapter extends FragmentPagerAdapter{
    public ArticleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleContentFragment.newInstance(position, "");
    }
}
