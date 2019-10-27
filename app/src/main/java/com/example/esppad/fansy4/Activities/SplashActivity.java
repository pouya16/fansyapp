package com.example.esppad.fansy4.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.esppad.fansy4.Classes.Categories;
import com.example.esppad.fansy4.Classes.MyApi;
import com.example.esppad.fansy4.Classes.RequestHelper;
import com.example.esppad.fansy4.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {
    Button btnEnter;
    MyApi api;
    JSONObject CategoriesObject;
    JSONArray FirstCategoriesArray;
    int tabCount = 0;
    ArrayList<String> tabNames = new ArrayList<>();
    final ArrayList<Categories> categoriesArray = new ArrayList<>();
    boolean isSuccess = false;
    int status = 0;
    int isFetchFinished =0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btnEnter = (Button) findViewById(R.id.btnAppEneter);
        api = new MyApi();
        int havePermision = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.i("Log","permision = " + havePermision);
        if(havePermision==0){
            api.createOrOpenDataBase(SplashActivity.this);
            clearFirstCategoryTable(api.getDatabase());
            clearSecondCategoryTable(api.getDatabase());
            clearThirdCategoryTable(api.getDatabase());
            getCategories();
        }else{
            requestForWriteSDCardPermission();
        }



        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Log2","btn enter is clicked");

                if (isFetchFinished == 1) {
                    Log.i("Log2","btn enter is clicked and is fetch is 1");
                    if (status == 0) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        SplashActivity.this.startActivity(intent);
                    } else if (status == 1) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        SplashActivity.this.startActivity(intent);
                    }

                }
            else{
                    Log.i("Log2","btn enter is clicked and is fetch is 0");
                    Toast.makeText(SplashActivity.this,"در حال دریافت اطلاعات از سرور",Toast.LENGTH_SHORT);
                }
            }
        });


    }


    //checking status to decide where applications go .... whether go to registration process or first page;
    private int checkStatus() {
        int situation = 0;
        String query = "SELECT * FROM user";
        SQLiteDatabase db = api.getDatabase();
        Cursor cursor = db.rawQuery(query,null);
        try{
            cursor.moveToNext();
            situation = 1;
        }catch (Exception e){
            situation = 0;
        }
        return situation;
    }

    //request permision part
    public void requestForWriteSDCardPermission(){
        RequestHelper request = new RequestHelper(this);
        RequestHelper.OnGrantedListener grantedListenerListener = new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
                api.createAppDirectories(SplashActivity.this);
                api.createOrOpenDataBase(SplashActivity.this);
                status = checkStatus();
                Log.i("Log","before go to categories");
                getCategories();

            }
        };

        RequestHelper.OnAlreadyGrantedListener onAlreadyGrantedListener = new RequestHelper.OnAlreadyGrantedListener() {
            @Override
            public void onAlreadyGranted() {
                api.createAppDirectories(SplashActivity.this);
                api.createOrOpenDataBase(SplashActivity.this);
                status = checkStatus();
                Log.i("Log","before go to categories");
                getCategories();

            }
        };

        RequestHelper.OnDeniedListener deniedListener = new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                new AlertDialog.Builder(SplashActivity.this)
                        .setTitle("Permission Required")
                        .setMessage("Writing to SDCARD required for this app")
                        .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestForWriteSDCardPermission();
                            }
                        })
                        .create()
                        .show();
            }
        };
        request.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, grantedListenerListener, onAlreadyGrantedListener , deniedListener);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        RequestHelper.onRequestPermissionResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //connect to api and entering data to db
    private void getCategories(){
        OkHttpClient client = new OkHttpClient();
        Request request = api.getCategoryRequest();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SplashActivity.this,"Failed to connect to to server",Toast.LENGTH_SHORT).show();
                        Log.i("Log","Failed to connect to to server, "+ e.toString());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("Log6","onResponse");
                Log.i("Log6","response is: " + response.toString());

                try {
                    CategoriesObject = new JSONObject(response.body().string());
                    Log.i("Log6","object created");
                    if (CategoriesObject != null) {
                    Log.i("Log6","object is not null created");
                        FirstCategoriesArray = CategoriesObject.getJSONArray("data");
                        Log.i("Log6", "not null" + FirstCategoriesArray.toString());
                        tabCount = FirstCategoriesArray.length();
                        Log.i("Log6", "tab numbers are: " + tabCount);
                        for(int i=0;i<tabCount;i++){
                            JSONObject eachcategory = FirstCategoriesArray.getJSONObject(i);
                            JSONArray secondCategoryArray = eachcategory.getJSONArray("secondCategories");
                            Log.i("Log6","second array is: "+ secondCategoryArray.toString());
                            Log.i("Log6","array 1 had made");
                            enterFirstCategory(eachcategory,api.getDatabase(),secondCategoryArray.length());
                            Log.i("Log6","after entering first category");
                            String categoryId = String.valueOf(10+i);
                            Log.i("Log6","second category length is : " + secondCategoryArray.length());
                            if(secondCategoryArray.length()>0){
                                for(int j=0;j<secondCategoryArray.length();j++){
                                    JSONObject secondCategory = secondCategoryArray.getJSONObject(j);
                                    JSONArray lastCategoryArray = secondCategory.getJSONArray("lastCategories");
                                    Log.i("Log6","array 2 had made");
                                    enterSecondCategory(secondCategory,categoryId,api.getDatabase(),lastCategoryArray.length());
                                    String secCatID = secondCategory.getString("categoryId");
                                    if(lastCategoryArray.length()>0){
                                        for(int k=0;k<lastCategoryArray.length();k++){
                                            JSONObject lastCategory = lastCategoryArray.getJSONObject(k);
                                            enterLastCategory(lastCategory,secCatID,api.getDatabase());
                                            Log.i("Log6","array 3 had made");
                                        }
                                    }
                                }
                            }

                        }
                        isFetchFinished = 1;
                        //Log.i("Log7","tabCount is: "+ tabNames.size());
                        //test.setText(CategoriesObject.toString());
                    } else {
                       // Log.i("LOG5", "null");
                        //test.setText("null object");
                    }
                   // Log.i("Log", response.body().toString());
                   // Log.i("Log2", response.toString());
                   // Log.i("Log3", CategoriesObject.toString());
                } catch (JSONException e) {
                    Log.i("Log","JSON Exception" + e.toString());
                    e.printStackTrace();
                }
                /*try {
                    boolean gettingToken = CategoriesObject.getBoolean("isSuccess");
                    isSuccess = gettingToken;
                    Log.i("Log4", "gettingToken is: " + gettingToken);
                    Log.i("Log5", "isSuccess is: " + isSuccess);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String text = "";
                if (isSuccess){

                }
                else{
                    text = "no token!";
                }
                final String finalText = text;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SplashActivity.this, ""+ finalText, Toast.LENGTH_SHORT).show();
                    }
                });*/




            }
        });
    }

    public int enterFirstCategory(JSONObject firstCategory, SQLiteDatabase db,int len){
        int success = 0;
        try {
            int visible = 0;
            int active = 0;
            String catTitle = firstCategory.getString("catTitle");
            String imageString = firstCategory.getString("catPicture");
            byte[] decodedImage = Base64.decode(imageString,Base64.DEFAULT);
            boolean isVisible = firstCategory.getBoolean("visible");
            boolean isActive = firstCategory.getBoolean("isActive");
            int userID = firstCategory.getInt("userId");
            int childrenNumber = len;
            String modified = firstCategory.getString("modified");
            if(isVisible){
                visible =1;
            }if(isActive){
                active = 1;
            }
            String query = "INSERT INTO 'firstcategory'('cattitle','catpicture','visible','isactive','userid','childrennumber','modified') values" +
                    "('" + catTitle + "', '" + decodedImage + "', '" +visible+ "', '" + active + "', '" + userID + "', '" + childrenNumber + "', '"+ modified + "')";
            db.execSQL(query);
            success = 1;
            Log.i("Log1", "input successfully in db");


        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("Log1", "input is not successfully in db" + e.getMessage());

        }
        return success;
    }

    public int enterSecondCategory(JSONObject firstCategory,String parentId, SQLiteDatabase db,int len){
        int success = 0;
        try {
            int visible = 0;
            int active = 0;
            String catTitle = firstCategory.getString("catTitle");
            String imageString = firstCategory.getString("catPicture");
            byte[] decodedImage = Base64.decode(imageString,Base64.DEFAULT);
            boolean isVisible = firstCategory.getBoolean("visible");
            boolean isActive = firstCategory.getBoolean("isActive");
            int userID = firstCategory.getInt("userId");
            int childrenNumber = len;
            String modified = firstCategory.getString("modified");
            if(isVisible){
                visible =1;
            }if(isActive){
                active = 1;
            }
            String query = "INSERT INTO 'secondcategory'('cattitle','catpicture','visible','isactive','userid','parentid','childrennumber','modified') values" +
                    "('" + catTitle + "', '" + decodedImage + "', '" +visible+ "', '" + active + "', '" + userID + "', '" + parentId + "', '" + childrenNumber + "', '"+ modified + "')";
            db.execSQL(query);
            success = 1;
            Log.i("Log2", "input successfully in db");


        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("Log2", "input is not successfully in db" + e.getMessage());

        }
        return success;
    }

    public int enterLastCategory(JSONObject firstCategory,String parentId, SQLiteDatabase db){
        int success = 0;
        try {
            int visible = 0;
            int active = 0;
            String catTitle = firstCategory.getString("catTitle");
            String imageString = firstCategory.getString("catPicture");
            byte[] decodedImage = Base64.decode(imageString,Base64.DEFAULT);
            boolean isVisible = firstCategory.getBoolean("visible");
            boolean isActive = firstCategory.getBoolean("isActive");
            int userID = firstCategory.getInt("userId");
            String modified = firstCategory.getString("modified");
            if(isVisible){
                visible =1;
            }if(isActive){
                active = 1;
            }
            String query = "INSERT INTO 'thirdcategory'('cattitle','catpicture','visible','isactive','userid','parentid','modified') values" +
                    "('" + catTitle + "', '" + decodedImage + "', '" +visible+ "', '" + active + "', '" + userID + "', '" + parentId + "', '"+ modified + "')";
            db.execSQL(query);
            success = 1;
            Log.i("Log3", "input successfully in db");


        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("Log3", "input is not successfully in db" + e.getMessage());

        }
        return success;
    }

    public void clearFirstCategoryTable(SQLiteDatabase db){
        String query = "DELETE FROM firstcategory";
        try{
            db.execSQL(query);
            Log.i("Log-d", "deleting complete");
        }catch (Exception e){
            Log.i("Log-d", "deleting problem: " + e.toString());
        }


    }

    public void clearSecondCategoryTable(SQLiteDatabase db){
        String query = "DELETE FROM secondcategory";
        try{
            db.execSQL(query);
            Log.i("Log-d", "deleting complete");
        }catch (Exception e){
            Log.i("Log-d", "deleting problem: " + e.toString());
        }

    }

    public void clearThirdCategoryTable(SQLiteDatabase db){
        String query = "DELETE FROM thirdcategory";
        try{
            db.execSQL(query);
            Log.i("Log-d", "deleting complete");
        }catch (Exception e){
            Log.i("Log-d", "deleting problem: " + e.toString());
        }

    }




}
