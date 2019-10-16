package com.example.esppad.fansy4.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.esppad.fansy4.Classes.Api;
import com.example.esppad.fansy4.Classes.Categories;
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


public class CategoriesFragment extends Fragment {
    public CategoriesFragment() {
        // Required empty public constructor
    }
    JSONObject CategoriesObject;
    JSONArray FirstCategoriesArray;
    int tabCount = 0;
    ArrayList<String> tabNames = new ArrayList<>();
    final ArrayList<Categories> categoriesArray = new ArrayList<>();
    boolean isSuccess = false;
    TextView test;
    Api api = new Api();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        test = (TextView) getActivity().findViewById(R.id.test);
        getCategories();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Log1","On resume");
        getCategories();
    }

    @Override
    public void onPause() {
        Log.i("Log1","on pause");
        super.onPause();
    }

    private void getCategories(){
        OkHttpClient client = new OkHttpClient();
        Request request = api.getCategoryRequest();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"Failed to connect to to server",Toast.LENGTH_SHORT).show();
                        Log.i("Log","Failed to connect to to server, "+ e.toString());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    try {
                        CategoriesObject = new JSONObject(response.body().string());
                        if (CategoriesObject != null) {
                            FirstCategoriesArray = CategoriesObject.getJSONArray("data");
                            Log.i("LOG5", "not null" + FirstCategoriesArray.toString());
                            tabCount = FirstCategoriesArray.length();
                            for(int i=0;i<tabCount;i++){
                                JSONObject eachcategory = FirstCategoriesArray.getJSONObject(i);
                                String catName = eachcategory.getString("catTitle");
                                String catPic = eachcategory.getString("catPicture");
                                boolean visible = eachcategory.getBoolean("visible");
                                boolean isActive = eachcategory.getBoolean("isActive");
                                int userId = eachcategory.getInt("userId");
                                String modified = eachcategory.getString("modified");
                                JSONObject secondCategory = eachcategory.getJSONObject("secondCategories");
                                Categories cat = new Categories(catName,visible,isActive,userId,catPic,modified,secondCategory);
                                categoriesArray.add(cat);
                                tabNames.add(catName);
                            }
                            Log.i("Log7","tabCount is: "+ tabNames.size());
                            //test.setText(CategoriesObject.toString());
                        } else {
                            Log.i("LOG5", "null");
                            //test.setText("null object");
                        }
                        Log.i("Log", response.body().toString());
                        Log.i("Log2", response.toString());
                        Log.i("Log3", CategoriesObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                try {
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), ""+ finalText, Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });
    }

}
