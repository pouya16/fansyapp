package com.example.esppad.fansy4.Adapters;

import android.database.Cursor;
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
        if(position<0||position>30){
            return null;
        }
        return TabLayoutFragment.newInstance(position);
    }
//Count the numbers of pages in viewpagers
    @Override
    public int getCount() {
        int pagesCount = 0;
        Log.i("Log10", "Start counting ");
        try{
            String query ="SELECT * FROM firstcategory";
            Cursor cursor = db.rawQuery(query,null);
            while(cursor.moveToNext()){
                pagesCount++;
            }
            Log.i("Log10","page count is: " + pagesCount);

        }catch (Exception e){
            Log.i("Log10","Cant Count ViewPagers Count: " + e.toString());
        }
        return pagesCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle ="";
        try{
            String query = "SELECT * FROM firstcategory";
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToPosition(position);
            pageTitle = cursor.getString(1);
            Log.i("Log10","pageTitle is: " + pageTitle);

        }catch (Exception e){
            Log.i("Log10","can not respawn category name: " + e.toString());
        }
        return pageTitle;
    }
}
