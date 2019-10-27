package com.example.esppad.fansy4.fragments;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.esppad.fansy4.Adapters.ListViewAdaptor;
import com.example.esppad.fansy4.Classes.Category;
import com.example.esppad.fansy4.Classes.MyApi;
import com.example.esppad.fansy4.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabLayoutFragment extends Fragment {
    private int position = 0;


    public TabLayoutFragment(int pos) {
        position = pos;
        // Required empty public constructor
    }

    MyApi api = new MyApi();
    Category category;
    ArrayList<Category> categoryList = new ArrayList<>();
    ListViewAdaptor adaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        api.createOrOpenDataBase(getActivity());
        Log.i("Log7","DataBase Opened Successfully");
        View view =  inflater.inflate(R.layout.fragment_tab_layout, container, false);
        ListView listView = (ListView) getActivity().findViewById(R.id.category_list_item);
        try {
            String query = "SELECT * FROM firstcategory WHERE autoId = '" + position + "'";
            Cursor cursor = api.getDatabase().rawQuery(query, null);
            Log.i("Log7","cursor had made and fetched data");
            cursor.moveToFirst();
            Log.i("Log7","cursor moved to first successfully");
            int child = cursor.getInt(5);
            String parentid = cursor.getString(0);
            int coded = Integer.parseInt(parentid);
            coded = coded - 10;
            if (child > 0) {
                String query1 = "SELECT * FROM secondcategory WHERE parentid = '" + coded + "'";
                Cursor cursor2 = api.getDatabase().rawQuery(query1, null);
                Log.i("Log7","cursor1 created successfully");
                while (cursor2.moveToNext()) {
                    String catTitle = cursor.getString(1);
                    byte[] byteImage = cursor.getBlob(2);
                    Bitmap catImage = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                    String id = cursor.getString(6);
                    int children = cursor.getInt(8);
                    Category currentCategory = new Category(id, catTitle, catImage, children);
                    categoryList.add(currentCategory);
                }
            } else {

            }
            if(categoryList!=null){
                adaptor = new ListViewAdaptor(getActivity(), categoryList);
                Log.i("Log7","adaptor had made");
            }else{
                Log.i("Log7","adaptor did not made");
            }
            if(adaptor!=null) {
                listView.setAdapter(adaptor);
                Log.i("Log7","ListView had made");
            }else{
                Log.i("Log7","ListView did not made");
            }
        }catch (Exception e){
            Log.i("Log7","Problem Reading tabs from db: " + e.toString());
        }

        return view;
    }

    public static TabLayoutFragment newInstance(int pos) {

        Bundle args = new Bundle();

        TabLayoutFragment fragment = new TabLayoutFragment(pos);
        fragment.setArguments(args);
        return fragment;
    }

}
