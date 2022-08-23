package com.necode.voicetomylanguage.App;

import android.util.Log;


import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class RetrofitResponse {
    public String MainUrl;

    public RetrofitResponse(String mainUrl) {
        MainUrl = mainUrl;
    }

    public void Get(String method_url, HttpCallback httpCallback) {
        /** Retrofit **/

        ConnectionPool pool = new ConnectionPool(5, 10000, TimeUnit.MILLISECONDS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.MainUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyRequest myApi = retrofit.create(MyRequest.class);
        myApi.GetData(method_url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    httpCallback.onSuccess(response.body().string());

                } catch (Exception e) {
                    httpCallback.onSuccess(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                httpCallback.onSuccess(t.getMessage());
            }

        });
        /** End Retrofit **/
    }

    public interface MyRequest {
        @GET
        Call<ResponseBody> GetData(@Url String url);
    }

    public interface HttpCallback {
        public void onSuccess(String response);
    }
}
