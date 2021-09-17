package com.example.newapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newapp.activity.BaseActivity;
import com.example.newapp.activity.LoginActivity;
import com.example.newapp.activity.RegisterActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=  new Intent(getApplicationContext(), LoginActivity.class);
                // startActivity(intent);

                Navigate(LoginActivity.class);
            }
        });


        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=  new Intent(getApplicationContext(), RegisterActivity.class);
                //startActivity(intent);

                Navigate(RegisterActivity.class);

            }
        });
    }
}