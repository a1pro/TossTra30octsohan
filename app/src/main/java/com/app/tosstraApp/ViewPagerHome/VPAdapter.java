package com.app.tosstraApp.ViewPagerHome;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.tosstraApp.models.AllJobsToDriver;

public class VPAdapter extends FragmentStatePagerAdapter {

    FragmentManager fm;
    AllJobsToDriver data;
    public VPAdapter(@NonNull FragmentManager fm, AllJobsToDriver data) {
        super(fm);
        this.fm=fm;
        this.data=data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(position,data);
    }

    @Override
    public int getCount() {
        if(data.getData()!=null)
        return data.getData().size();
        else
            return 0;
    }
}
