package com.example.esppad.fansy4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.esppad.fansy4.Classes.Category;
import com.example.esppad.fansy4.R;

import java.util.List;

/**
 * Created by pouya on 10/12/2018.
 */

public class ListViewAdaptor extends ArrayAdapter<Category> {

    public ListViewAdaptor(@NonNull Context context, List<Category> CategoryList) {
        super(context,0, CategoryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.services_layer2_listview,parent,false);
        }
        Category currentCategory = getItem(position);

        TextView catTitle = (TextView) listItemView.findViewById(R.id.ListViewCatTitle);
        catTitle.setText(currentCategory.getCatTitle());
        ImageView catImage = (ImageView) listItemView.findViewById(R.id.ListViewImage);
        catImage.setImageBitmap(currentCategory.getCatPicture());

        return listItemView;

    }

}
