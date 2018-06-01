package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragments.EPLFragments.FixturesFragment;
import Fragments.EPLFragments.TableFragment;
import Fragments.EPLFragments.TeamsFragment;

public class ViewPagerAdapterEPL extends FragmentPagerAdapter {
    public String[] title = {"Table", "Fixtures", "Teams"};

    public ViewPagerAdapterEPL(FragmentManager manager) {
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
