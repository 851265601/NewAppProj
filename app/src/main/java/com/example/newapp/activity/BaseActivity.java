package com.example.newapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mContext = this;
        super.onCreate(savedInstanceState);
    }

    public void ShowToast(String str) {

        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();

    }

    public void Navigate(Class cls) {

        Intent intent = new Intent(mContext, cls);
        startActivity(intent);

    }

}