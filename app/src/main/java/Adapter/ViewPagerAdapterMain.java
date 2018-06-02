package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragments.MainFragments.MainFragment1;
import Fragments.TabFragment2;

public class ViewPagerAdapterMain extends FragmentPagerAdapter{
    private String[] title = {"NewsFeed", "two", "three"};
    public ViewPagerAdapterMain(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return MainFragment1.getInstance(position);
        }
        else {
            return TabFragment2.getInstance(position);
        }
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
