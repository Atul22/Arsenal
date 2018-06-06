package com.example.atul.arsenal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import Adapter.ViewPagerAdapter;
import Adapter.ViewPagerAdapterMain;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
        NavigationView navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        ViewPagerAdapterMain adapter = new ViewPagerAdapterMain(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.v("JSON3", item.getTitle().toString());
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                if (item.getTitle().toString().equals(getString(R.string.arsenal))) {
                        startActivity(new Intent(MainActivity.this, VideosActivity.class));
                } else if (item.getTitle().toString().equals(getString(R.string.EPL))) {
                    startActivity(new Intent(MainActivity.this, EPLActivity.class));
                } else if(item.getTitle().toString().equals(getString(R.string.debate))) {
                    startActivity(new Intent(MainActivity.this, DebateActivity.class));
                } else if(item.getTitle().toString().equals(getString(R.string.bundesliga))) {
                    startActivity(new Intent(MainActivity.this, BundesLigaActivity.class));
                } else if(item.getTitle().toString().equals(getString(R.string.laliga))) {
                    startActivity(new Intent(MainActivity.this, LaLigaActivity.class));
                } else if(item.getTitle().toString().equals(getString(R.string.seriea))) {
                    startActivity(new Intent(MainActivity.this, SerieA_Activity.class));
                } else if(item.getTitle().toString().equals(getString(R.string.ligue1))) {
                    startActivity(new Intent(MainActivity.this, Ligue1Activity.class));
                } else if(item.getTitle().toString().equals(getString(R.string.wc))) {
                    startActivity(new Intent(MainActivity.this, WorldCupActivity.class));
                }else if(item.getTitle().toString().equals(getString(R.string.cl))) {
                    startActivity(new Intent(MainActivity.this, ChampionsLeagueActivity.class));
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
