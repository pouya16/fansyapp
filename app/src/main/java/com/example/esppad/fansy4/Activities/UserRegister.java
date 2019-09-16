package com.example.esppad.fansy4.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esppad.fansy4.Classes.constantVariables;
import com.example.esppad.fansy4.R;

public class UserRegister extends AppCompatActivity {
    constantVariables constants;
    EditText txtFamily;
    EditText txtName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        constants = new constantVariables();
        txtFamily = (EditText) findViewById(R.id.txtRegisterFamily);
        txtName = (EditText) findViewById(R.id.txtRegisterName);
        txtFamily.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    txtFamily.setHint("مثال: محمدی");
                }else{
                    txtFamily.setHint("");
                }
            }
        });
        txtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    txtName.setHint("مثال: محمد");
                }else{
                    txtName.setHint("");
                }
            }
        });
    }
}
