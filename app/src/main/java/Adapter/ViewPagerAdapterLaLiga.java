package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragments.LaLigaFragments.*;

public class ViewPagerAdapterLaLiga extends FragmentPagerAdapter {
    public String[] title = {"Table", "Fixtures", "Teams"};

    public ViewPagerAdapterLaLiga(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return TableFragment.getInstance(position);
        }
        else if(position == 1) {
            return FixturesFragment.getInstance(position);
        }
        else {
            return TeamsFragment.getInstance(position);
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
