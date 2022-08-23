package com.necode.voicetomylanguage.App;


import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GsonResponse {


    public static String startUrl(final String urlAddrass, final HttpCallback httpCallback, final FragmentActivity activity) {
        if (!FunctionHelper.CheckNetwork(activity)) {
            Toast.makeText(activity, "لطفا اینترنت خود را چک کنید", Toast.LENGTH_SHORT).show();
            return "";
        }
        OkHttpClient client = new OkHttpClient();
        client.retryOnConnectionFailure();
        Request request = new Request.Builder()
                .url(urlAddrass)
                .build();
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallback.onSuccess(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                httpCallback.onSuccess(string);
            }
        };
        client.newCall(request).enqueue(callback);
        return "";
    }


    public static String PostUrlNormal(final String urlAddrass, final HttpCallback httpCallback, final FragmentActivity activity, RequestBody requestBody) {
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("limit", Limit + "")
//                .addFormDataPart("offset", OffSet + "")
//                .addFormDataPart("id", "")
//                .addFormDataPart("text", search.getText().toString().trim())
//                .build();

        if (!FunctionHelper.CheckNetwork(activity)) {

            Toast.makeText(activity, "لطفا اینترنت خود را چک کنید", Toast.LENGTH_SHORT).show();
            return "";
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlAddrass)
                .post(requestBody)
                .build();
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.wtf("dsdsadsadsadsadasdsadsadsad", e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    // manage error
                    Log.wtf("dsdsadsadsadsadasdsadsadsad", response+"");

                    return;
                }
                // show body content
                final String string = response.body().string();
                httpCallback.onSuccess(string);


            }
        };

        client.newCall(request).enqueue(callback);
        return "";
    }


    public static String PostUrlAuth(final String urlAddrass, final HttpCallback httpCallback, final FragmentActivity activity, RequestBody requestBody) {
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("limit", Limit + "")
//                .addFormDataPart("offset", OffSet + "")
//                .addFormDataPart("id", "")
//                .addFormDataPart("text", search.getText().toString().trim())
//                .build();
        if (!FunctionHelper.CheckNetwork(activity)) {

            Toast.makeText(activity, "لطفا اینترنت خود را چک کنید", Toast.LENGTH_SHORT).show();
            return "";
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlAddrass)
                .addHeader("Authorization", "Bearer " + new Utili(activity).GetData("token"))
                .post(requestBody)
                .build();
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

//                try {
//                    new Utili(activity).InsertData("token", "");
//                    activity.finish();
//                } catch (Exception e2) {
//
//                }

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) {

//                    try {
//                        new Utili(activity).InsertData("token", "");
//                        activity.finish();
//                    } catch (Exception e2) {
//
//                    }

                    return;
                }

                try {
                    // show body content
                    final String string = response.body().string();

                    httpCallback.onSuccess(string);

                } catch (Exception e2) {
//                    new Utili(activity).InsertData("token", "");
//                    activity.finish();
                }
            }
        };

        client.newCall(request).enqueue(callback);
        return "";
    }


    public interface HttpCallback {
        public void onSuccess(String response);
    }

}


