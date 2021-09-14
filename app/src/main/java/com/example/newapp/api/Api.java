package com.example.newapp.api;

import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {

    public static Api api = new Api();
    private static HashMap<String, Object> mParams;
    private static OkHttpClient client;
    private static String requestUrl;

    public Api() {


    }

    public static Api config(String url, HashMap<String, Object> params) {
        client = new OkHttpClient.Builder().build();
        mParams = params;
        requestUrl = url;
        return api;
    }

    public void PostRequest(Context context, final TtitCallBack callback) {
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);
        //创建Request
        Request request = new Request.Builder()
                .url(ApiConfig.BASE_URl + ApiConfig.LOGIN)
                .addHeader("contentType", "application/json;charset=utf-8")
                .post(requestBodyJson)
                .build();
        //创建Call 回调
        final Call call = client.newCall(request);
        //发起请求
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                callback.OnSuccess(response.body().string());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }
        });
    }
}
