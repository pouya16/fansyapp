package com.example.esppad.fansy4.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "F1001";
    public static final int DB_VERSION =1 ;
    public static final String directory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/fansy/sampleDatabase";

    public MyDatabaseHelper(Context context) {
        super(context, directory + "/" + DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createFirstCategory(db);
        createSecondCategory(db);
        createThirdCategory(db);
        createUserStatus(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public void createFirstCategory(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'firstcategory' (" +
                        //0:
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'cattitle' TEXT , " +
                        "'catpicture' BLOB, " +
                        "'visible' INTEGER, " +
                        "'isactive' INTEGER, " +
                        "'userid' INTEGER, " +
                        "'childrennumber' INTEGER, " +
                        //6:
                        "'modified' TEXT " +
                        ")";

        db.execSQL(query);
    }

    public void createSecondCategory(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'secondcategory' (" +
                        //0
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'cattitle' TEXT, " +
                        "'catpicture' BLOB, " +
                        "'visible' INTEGER, " +
                        //4
                        "'isactive' INTEGER, " +
                        "'userid' INTEGER, " +
                        "'categoryid' TEXT, " +
                        //7
                        "'parentid' TEXT, " +
                        "'childrennumber' INTEGER, " +
                        "'modified' TEXT" +
                        ")";

        db.execSQL(query);
    }
    public void createThirdCategory(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'thirdcategory' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'cattitle' TEXT, " +
                        "'catpicture' BLOB, " +
                        "'visible' INTEGER, " +
                        "'isactive' INTEGER, " +
                        "'userid' INTEGER, " +
                        "'categoryid' TEXT, " +
                        "'parentid' TEXT, " +
                        "'modified' TEXT" +
                        ")";

        db.execSQL(query);
    }

    public void createUserStatus(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'user' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'phoneNumber' INTEGER UNIQUE, " +
                        "'status' INTEGER" +
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
            Log.e("Log-d",""+e.getMessage());
            Log.i("Log", "Failed to add machine");
        }
    }


}