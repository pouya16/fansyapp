package com.example.esppad.fansy4.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.esppad.fansy4.Adapters.AdaptorViewPager;
import com.example.esppad.fansy4.Classes.MyApi;
import com.example.esppad.fansy4.R;


public class CategoriesFragment extends Fragment {
    public CategoriesFragment() {
        // Required empty public constructor
    }

    MyApi api = new MyApi();
    TableLayout tabLayout;
    ViewPager viewPager;
    AdaptorViewPager adaptorViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        tabLayout = (TableLayout) getActivity().findViewById(R.id.tablayout_categories);
        viewPager =(ViewPager) getActivity().findViewById(R.id.viewpager_category);
        try{
            api.createOrOpenDataBase(getActivity());
        }catch (Exception e){
            Toast.makeText(getActivity(),"failed to connect to database",Toast.LENGTH_SHORT);
        }
        SQLiteDatabase db= api.getDatabase();
        int pagesCount = getCount(db);
        String[] pageTitle = new String[pagesCount];
        for(int i = 0;i<pagesCount;i++){
            pageTitle[i] = getPageTitle(db,i);
        }
        Log.i("Log9","number of tabs is: " + pagesCount);
        Log.i("Log9","tab names are: " + pageTitle.toString());
        adaptorViewPager = new AdaptorViewPager(getActivity().getSupportFragmentManager(),pagesCount,pageTitle);
        Log.i("Log9","adaptorViewPager is: " + adaptorViewPager.toString());
        if(adaptorViewPager!=null) {
            viewPager.setAdapter(adaptorViewPager);
            tabLayout.addView(viewPager);
        }
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.i("Log1","On resume");
        //getCategories();
    }

    @Override
    public void onPause() {
        Log.i("Log1","on pause");
        super.onPause();
    }

    private String getPageTitle(SQLiteDatabase db, int position) {
        String pageTitle ="";
        try{
            String query = "SELECT * FROM firstcategory";
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToPosition(position);
            pageTitle = cursor.getString(1);
            Log.i("Log9","pageTitle is: " + pageTitle);

        }catch (Exception e){
            Log.i("Log9","can not respawn category name: " + e.toString());
        }
        return pageTitle;

    }

    private int getCount(SQLiteDatabase db) {
        int pagesCount = 0;
        Log.i("Log9", "Start counting ");
        try{
            String query ="SELECT * FROM firstcategory";
            Cursor cursor = db.rawQuery(query,null);
            while(cursor.moveToNext()){
                pagesCount++;
            }
            Log.i("Log9","page count is: " + pagesCount);

        }catch (Exception e){
            Log.i("Log9","Cant Count ViewPagers Count: " + e.toString());
        }
        return pagesCount;
    }



}
