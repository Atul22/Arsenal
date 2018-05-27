package com.example.atul.arsenal;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import Adapter.ViewPagerAdapterEPL;

public class EPLActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epl);

        toolbar = findViewById(R.id.epl_toolbar);
        tabLayout = findViewById(R.id.epl_tabs);
        viewPager = findViewById(R.id.epl_viewpager);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        ViewPagerAdapterEPL mAdapter = new ViewPagerAdapterEPL(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

        tabLayout.setupWithViewPager(viewPager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EPLActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}