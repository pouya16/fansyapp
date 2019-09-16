package com.example.esppad.fansy4.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;


public class G {
    public static G app;

    public static SQLiteDatabase database;
    public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String BRAND_DIR = SDCARD + "/espad";
    public static final String APP_DIR = BRAND_DIR + "/sample_database";
    public static final String DB_DIR = APP_DIR + "/db";
    public static final String directory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/espad/sampleDatabase";

    public boolean checkInternetConnection(){

        return true;
    }



    public void createAppDirectories(Context context) {
        File dbDir = new File(DB_DIR);

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
}
