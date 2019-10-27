package com.example.esppad.fansy4.Adapters;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.esppad.fansy4.fragments.TabLayoutFragment;

//category viewpager adaptor
public class AdaptorViewPager extends FragmentPagerAdapter {
    SQLiteDatabase db;
    int tabsNumber;
    String[] tabNames;


    public AdaptorViewPager(@NonNull FragmentManager fm,int tabCount,String[] tabnames) {
        super(fm);
        tabsNumber=tabCount;
        tabNames = tabnames;
    }

    public AdaptorViewPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.i("Log10", "Start StartFragments ");
        if(position<0||position>position){
            return null;
        }
        return TabLayoutFragment.newInstance(position);
    }
//Count the numbers of pages in viewpagers
    @Override
    public int getCount() {

        return tabsNumber;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle =tabNames[position];
        return pageTitle;
    }
}
