package com.tongchengtianqi.http;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 1. 类的用途
 * 2. @author dell
 * 3. @date 2017/3/19 19:06
 */

public class OkhttpRequest {
    public static <T> void getHttp(String url, final Class<T> clazz, final CallBackToData callBackToData){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                T homeData=gson.fromJson(json,clazz);
                callBackToData.backData(homeData);
            }

        });
    }
    public interface CallBackToData<T>{
        void backData(T data);
    }
   
}
