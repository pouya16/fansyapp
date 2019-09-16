package com.example.esppad.fansy4.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Created by pouya on 4/25/2019.
 * A class for connecting to fansy api
 */

public class Api {

    //internet API addresses:
    private static final String ROOT_URL = "185.79.157.79/api/v1/";
    private static final String TOKEN_EXTRA = "OldUser/Token";
    private static final String USERS_EXTRA = "OldUser/";
    public static final String TOKEN_URL = ROOT_URL + TOKEN_EXTRA;
    public static final String GET_USERS_URL = ROOT_URL + USERS_EXTRA;

    //Internal DataBase addresses:
    public static SQLiteDatabase database;
    public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String BRAND_DIR = SDCARD + "/espad";
    public static final String APP_DIR = BRAND_DIR + "/sample_database";
    public static final String DB_DIR = APP_DIR + "/db";
    public static final String directory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/espad/sampleDatabase";


    //Methods:

    //DataBase Handler Methods:
    //create Directory for database
    public void createAppDirectories(Context context) {
        File dbDir = new File(DB_DIR);

        if (!dbDir.exists()) {
            boolean wasCreated = dbDir.mkdirs();
            Toast.makeText(context, "Already made", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Not Made", Toast.LENGTH_LONG).show();

        }
    }
    //create database or open database fo further use:


    //Internet connection Methods


    public static String getTokenUrl() {
        return TOKEN_URL;
    }

    public static String getGetUsersUrl() {
        return GET_USERS_URL;
    }



}
