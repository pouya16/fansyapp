package com.example.esppad.fansy4.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "fansy";
    public static final int DB_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, G.directory + "/" + DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createPhoneRegister(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            createPhoneRegister(db);
        }
        if(newVersion == 3){
            createPhoneRegister(db);
        }
    }



    public void createPhoneRegister(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'phoneregiste' (" +
                        //0:
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'phone' TEXT UNIQUE, " +
                        ")";

        db.execSQL(query);
    }

    public void createUsers(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'users' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'userId' INTEGER UNIQUE, " +
                        "'userName' TEXT UNIQUE, " +
                        "'password' TEXT" +
                        ")";

        db.execSQL(query);
    }

    public void createMachines(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'machines' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'machineNumber' INTEGER UNIQUE, " +
                        "'salonNumber' INTEGER" +
                        ")";

        db.execSQL(query);
    }

    public void createVeawers(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'weavers' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'weaverID' INTEGER UNIQUE, " +
                        "'weaverName' TEXT" +
                        ")";

        db.execSQL(query);
    }

    public void createPrivateReport(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'privateReport' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'messageKey' INTEGER UNIQUE, " +
                        "'message' TEXT" +
                        ")";

        db.execSQL(query);
    }

    public void postPrivateReport(SQLiteDatabase db,String data,int date,int shift){
        String dateShift = "" + date + shift;
        int key = Integer.parseInt(dateShift);
        try {
            String query = "INSERT INTO 'privateReport'('messageKey','message') values " +
                    "('" + key + "', '" + data + "')";
            db.execSQL(query);
            Log.i("Log", "private message successfully added");
        }catch (Exception e){
            Log.e("Log",""+e.getMessage());
            Log.i("Log", "Failed to add private message");
        }
    }

    public void postMachine(SQLiteDatabase db,int machine,int salon){
        try {
            String query = "INSERT INTO 'machines'('machineNumber','salonNumber') values " +
                    "('" + machine + "', '" + salon + "')";
            db.execSQL(query);
            Log.i("Log", "machine successfully added");
        }catch (Exception e){
            Log.e("Log",""+e.getMessage());
            Log.i("Log", "Failed to add machine");
        }
    }

    public void postUser(SQLiteDatabase db,String userID,String userName,String password){
        try {
            int userid = Integer.parseInt(userID);
            String query = "INSERT INTO 'users'('userId','userName','password') values " +
                    "('" + userid + "', '" + userName + "', '"+ password + "')";
            db.execSQL(query);
            Log.i("Log", "machine successfully added");
        }catch (Exception e){
            Log.e("Log",""+e.getMessage());
            Log.i("Log", "Failed to add machine");
        }
    }
}