package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragments.EPLFragments.TableFragment;

public class ViewPagerAdapterEPL extends FragmentPagerAdapter {
    public String[] title = {"Table", "Fixtures", "Teams"};

    public ViewPagerAdapterEPL(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return TableFragment.getInstance(position);
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
