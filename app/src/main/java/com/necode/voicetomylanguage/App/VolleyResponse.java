package com.necode.voicetomylanguage.App;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class VolleyResponse {
    public HttpCallback httpCallback;
    public String Url;
    public FragmentActivity activity;


    public VolleyResponse(String url, HttpCallback httpCallback, FragmentActivity activity) {
        this.httpCallback = httpCallback;
        this.Url = url;
        this.activity = activity;
    }

    public void Get() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            httpCallback.onSuccess(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //do stuffs with response erroe
                    }
                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("token", "message");
//                params.put("usr", "bimeh_market");
//                params.put("psw", "usrbimehpsw");
//                params.put("user_id", shp.getString("user_id", "0"));
//                Log.d("Delsa_jsonObject", String.valueOf(params));
//                return params;
//            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }


    public class YourRequest extends StringRequest {
        public YourRequest(String url, Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
            super(url, listener, errorListener);
            setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
    }

    public interface HttpCallback {
        public void onSuccess(String response);
    }
}
