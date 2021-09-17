package com.example.newapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.newapp.R;
import com.example.newapp.api.Api;
import com.example.newapp.api.ApiConfig;
import com.example.newapp.api.TtitCallBack;
import com.example.newapp.util.StringUtils;

import java.util.HashMap;


public class LoginActivity extends BaseActivity {


    EditText et_account;
    EditText et_pwd;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_account = findViewById(R.id.et_account);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Account = et_account.getText().toString().trim();
                String Password = et_pwd.getText().toString().trim();
                Login(Account, Password);
            }


        });

    }

    //登录账号账号密码判断是否为空
    private void Login(String account, String password) {

        if (StringUtils.IsStringEmpty(account)) {
            ShowToast("No Account");
            return;
        }
        if (StringUtils.IsStringEmpty(password)) {
            ShowToast("No Password");
            return;
        }
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("mobile", account);
        m.put("password", password);
        Api.config(ApiConfig.LOGIN, m).PostRequest(this, new TtitCallBack() {
            @Override
            public void OnSuccess(String responseContent) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowToast(responseContent);
                    }
                });
            }

            @Override
            public void onFailure(Exception exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowToast(exception.getMessage());
                    }
                });
            }
        });
        //登录账号：root
        //密码：123456
        //创建Http对象
//        OkHttpClient client=new OkHttpClient.Builder().build();
//        Map m=new HashMap();
//        m.put("mobile",account);
//        m.put("password",password);
//
//        JSONObject jsonObject=new JSONObject(m);
//        String jsonStr=jsonObject.toString();
//        RequestBody requestBodyJson=   RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonStr);
//        //创建Request
//        Request request=  new Request.Builder()
//                .url(AppConfig.Base_URl+"/app/login")
//                .addHeader("contentType","application/json;charset=utf-8")
//                .post(requestBodyJson)
//                .build();
//        //创建Call 回调
//        final Call call=client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ShowToast(e.getMessage());
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String responseContent=response.body().toString();
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ShowToast(responseContent);
//                    }
//                });
//
//            }
//        });
    }


}