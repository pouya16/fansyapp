package com.example.esppad.fansy4.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

import okhttp3.Request;

/**
 * Created by pouya on 4/25/2019.
 * A class for connecting to fansy api
 */

public class Api {

    //internet API addresses:
    private static final String ROOT_URL = "185.79.157.79/api/v1/";
    private static final String TOKEN_EXTRA = "OldUser/Token";
    private static final String CTEGORY_EXTRA = "Categories";
    private static final String USERS_EXTRA = "OldUser/";
    public static final String TOKEN_URL = ROOT_URL + TOKEN_EXTRA;
    public static final String GET_USERS_URL = ROOT_URL + USERS_EXTRA;

    //Internal DataBase addresses:
    public static SQLiteDatabase database;
    public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String BRAND_DIR = SDCARD + "/fansy";
    public static final String APP_DIR = BRAND_DIR + "/sampleDatabase";
    public static final String DB_DIR = APP_DIR + "/db";
    public static final String directory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/fansy/sampleDatabase";


    //Methods:

    //DataBase Handler Methods:
    //create Directory for database

    //create database or open database fo further use:

    public void createAppDirectories(Context context) {
        File dbDir = new File(directory);

        if (!dbDir.exists()) {
            boolean wasCreated = dbDir.mkdirs();
            Toast.makeText(context, "Already made",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context, "Not Made",Toast.LENGTH_LONG).show();
        }
    }

    public void createOrOpenDataBase(Context context){
        if(database!=null){
            return;
        }
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    //Internet connection Methods


    public static String getTokenUrl() {
        return TOKEN_URL;
    }

    public static String getGetUsersUrl() {
        return GET_USERS_URL;
    }

    public Request getCategoryRequest(){
        Request request = new Request.Builder()
                .url(ROOT_URL+CTEGORY_EXTRA)
                .get()
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Host", "185.79.157.79:83")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("cache-control", "no-cache")
                .build();
        return request;
    }



}
