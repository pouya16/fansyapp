package com.example.esppad.fansy4.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.esppad.fansy4.Classes.constantVariables;
import com.example.esppad.fansy4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText phoneNumber;
    Button btnLater;
    Button btnInviteCode;
    Button btnContinue;
    String gotPhoneNumber = "";
    OkHttpClient client;
    OkHttpClient client1;
    String token = "";
    String Authorization = "";
    JSONObject tokenObject;
    JSONObject insideToken;
    JSONObject categoriesJSONObject;
    constantVariables constants;
    boolean isSuccess = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        btnLater = (Button) findViewById(R.id.btnLater);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnInviteCode = (Button) findViewById(R.id.btnInviteCodeDialog);
        client = new OkHttpClient();
        client1 = new OkHttpClient();
        btnContinue.setEnabled(false);
        constants = new constantVariables();
        Log.i("Log20","Entering getting firebase Section: ");
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()){
                    Log.i("Log20", "getInstanceId failed", task.getException());
                    return;
                }
                String token = task.getResult().getToken();
                Log.i("Log20","firebase token is: "+ token);
            }
        });



        //making button enable on valid phone number
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String phone = phoneNumber.getText().toString();
                if(phone.startsWith("09")&&phone.length()==11){
                    btnContinue.setEnabled(true);
                }else{
                    btnContinue.setEnabled(false);
                }

            }
        });

        //btnContinueClick
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotPhoneNumber = phoneNumber.getText().toString();
                constants.phoneNumber = gotPhoneNumber;
                getToken(gotPhoneNumber);
            }
        });
    }





    //functions

    //getToken function for connecting to server and get back token
    private void getToken(String phoneNumber){
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "Grant_Type=password&username="+phoneNumber);
        Request request = new Request.Builder()
                .url("http://185.79.157.79:83/api/v1/User/Token")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"Failed to connect to to server",Toast.LENGTH_SHORT).show();
                        Log.i("Log","Failed to connect to to server, "+ e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    tokenObject = new JSONObject(response.body().string());
                    Log.i("Log",response.body().toString());
                    Log.i("Log2",response.toString());
                    Log.i("Log3",tokenObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    boolean gettingToken = tokenObject.getBoolean("isSuccess");
                    isSuccess = gettingToken;
                    Log.i("Log4", "gettingToken is: " + gettingToken);
                    Log.i("Log5", "isSuccess is: " + isSuccess);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String text = "";
                if (isSuccess){
                    try {
                        insideToken = new JSONObject(String.valueOf(tokenObject.getJSONObject("data")));
                        token = insideToken.getString("access_token");
                        constants.ACCESS_TOKEN = token;
                        Authorization = insideToken.getString("token_type");
                        text = "getting token successfully finished!";
                        Intent intent = new Intent(MainActivity.this,MessageLogin.class);
                        MainActivity.this.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    text = "no token!";
                    Intent intent = new Intent(MainActivity.this,UserRegister.class);
                    MainActivity.this.startActivity(intent);
                }
                final String finalText = text;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, ""+ finalText, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }





}
