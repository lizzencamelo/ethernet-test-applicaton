package com.example.networkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.StrictMode;

import com.example.networkapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdaptor viewPagerAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdaptor.addFragment(new Tab1(), "NETWORK LIST");
        viewPagerAdaptor.addFragment(new Tab2(), "PING TEST TOOL");
        viewPagerAdaptor.addFragment(new Tab5(), "IPERF UTILITY TOOL");
        viewPagerAdaptor.addFragment(new Tab3(), "TCPDUMP TOOL");
        viewPagerAdaptor.addFragment(new Tab4(), "DUMPSYS TOOL");

        viewPager.setAdapter(viewPagerAdaptor);

    }
}