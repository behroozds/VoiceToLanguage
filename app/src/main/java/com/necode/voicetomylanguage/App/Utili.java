package com.necode.voicetomylanguage.App;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


import androidx.fragment.app.FragmentActivity;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Utili {
    private FragmentActivity activity;
    private SharedPreferences shpre;
    private String SharedName = "ShopShpref";

    public Utili(FragmentActivity activity) {
        this.activity = activity;
        this.shpre = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public void InsertData(String ShName, String data) {
        shpre = activity.getSharedPreferences(SharedName, MODE_PRIVATE);
        SharedPreferences.Editor editor = shpre.edit();
        editor.putString(ShName, data);
        editor.apply();
    }



    public Boolean TokenStatus() {
        Boolean Res = false;

        shpre = activity.getSharedPreferences(SharedName, MODE_PRIVATE);
        String token = shpre.getString("token", "");

        if (token != null && !token.isEmpty()) {
            Res = true;
        }

        return Res;
    }

    public String GetData(String ShName) {
        shpre = activity.getSharedPreferences(SharedName, MODE_PRIVATE);
        return shpre.getString(ShName, "");
    }

}
