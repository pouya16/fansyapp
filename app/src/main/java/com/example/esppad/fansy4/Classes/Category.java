package com.example.esppad.fansy4.Classes;

import android.graphics.Bitmap;

/**
 * Created by pouya on 5/26/2019.
 */

public class Category {

    private String id = "";
    private String catTitle = "";
    private Bitmap catPicture = null;
    private int childrenNumber = 0;

    public Category(String id, String catTitle, Bitmap catPicture, int childrenNumber) {
        this.id = id;
        this.catTitle = catTitle;
        this.catPicture = catPicture;
        this.childrenNumber = childrenNumber;
    }

    public String getId() {
        return id;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public Bitmap getCatPicture() {
        return catPicture;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }
}
