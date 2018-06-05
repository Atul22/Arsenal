package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragments.LeagueFragments.*;

public class ViewPagerAdapterLeague extends FragmentPagerAdapter {
    public String[] title = {"Table", "Fixtures", "Teams"};
    private String leagueId;

    public ViewPagerAdapterLeague(FragmentManager manager, String id) {
        super(manager);
        leagueId = id;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            if(leagueId.equals("467")) {
                return TableFragmentChampionship.getInstance(position, leagueId);
            }
            return TableFragment.getInstance(position, leagueId);
        }
        else if(position == 1) {
            return FixturesFragment.getInstance(position, leagueId);
        }
        else {
            return TeamsFragment.getInstance(position, leagueId);
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
