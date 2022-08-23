package com.necode.voicetomylanguage.App;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.necode.voicetomylanguage.JsonData.LanguageResponse;
import com.necode.voicetomylanguage.JsonData.TranslateResponse;
import com.necode.voicetomylanguage.MainActivity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GoogleTranslate {

    String BaseUrl = "http://necode.ir/php_api/translate.php";

    FragmentActivity acticity;

    public GoogleTranslate(FragmentActivity mainActivity) {
        acticity = mainActivity;
    }

    public interface HttpCallback {
        public void onSuccess(String response);
    }

    public void Translate(String Text, String TextLanguage, String TargetLanguage, HttpCallback httpCallback) {


        BaseUrl = BaseUrl + "?source=" + TargetLanguage.trim() + "&target=" + TextLanguage.trim() + "&text=" + Text.replace(" ", "+");

        Log.wtf("dsdsadsadsadsadasdsadsadsad", "11" + BaseUrl);


        try {
            new VolleyResponse(BaseUrl, new VolleyResponse.HttpCallback() {
                @Override
                public void onSuccess(String response) {
                    Log.wtf("dsdsadsadsadsadasdsadsadsad", "11" + response);

                    ShowToast(response);

                }
            }, acticity).Get();


            new RetrofitResponse(BaseUrl).Get(BaseUrl, new RetrofitResponse.HttpCallback() {
                @Override
                public void onSuccess(String response) {
                    acticity.runOnUiThread(new Runnable() {
                        public void run() {
                            Log.wtf("dsdsadsadsadsadasdsadsadsad", "11" + response);

                            ShowToast(response);

                        }
                    });
                }
            });

        } catch (Exception e) {

            Log.wtf("dsdsadsdsadsadas", e.getMessage());

        }


    }

    private void ShowToast(String response) {
        CustomToastTranslate toastTranslate = new CustomToastTranslate(acticity);
        toastTranslate.show(response);
    }

}
