package com.stefanoiaconetti.personalfitness_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Stefano on 12/20/2017.
 */

public class CustomAdapter extends WorkoutViewPager{


    public CustomAdapter(FragmentPagerAdapter fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        return null;
    }


    @Override
    public int getCount(){
        return 3;
    }
}
