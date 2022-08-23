package com.necode.voicetomylanguage.App;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.necode.voicetomylanguage.MainActivity;

import java.util.ArrayList;


public class CustomSpeechRecognizer implements RecognitionListener {
    public FragmentActivity activity;
    HttpCallback httpCallback;
    String MyLang, TargetLang;

    public CustomSpeechRecognizer(String myLang, String TargetLang, FragmentActivity activity, HttpCallback httpCallback) {
        this.activity = activity;
        this.httpCallback = httpCallback;
        this.MyLang = myLang;
        this.TargetLang = TargetLang;
    }

    private static final String TAG = "MyActivity";

    public void onReadyForSpeech(Bundle params) {
        Log.d(TAG, "onReadyForSpeech");
    }

    public void onBeginningOfSpeech() {
        Log.d(TAG, "onBeginningOfSpeech");
    }

    public void onRmsChanged(float rmsdB) {


    }

    public void onBufferReceived(byte[] buffer) {
        Log.d(TAG, "onBufferReceived");
    }

    public void onEndOfSpeech() {
        Log.d(TAG, "onEndofSpeech");
        httpCallback.onSuccess(false, "");

    }

    public void onError(int error) {
        Log.d(TAG, "error " + error);
        httpCallback.onSuccess(true, "");

    }

    public void onResults(Bundle results) {
        String _voiceResults = "";
        try {
            if (results == null) {
                System.out.println("No voice results");
            } else {

                String str = new String();
                ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                for (int i = 0; i < data.size(); i++) {
//                    str += data.get(i);
//                }
                str += data.get(0);
                _voiceResults = str;
                Log.d(TAG, "onResults " + _voiceResults);

            }
        } catch (Exception e) {
        }

        /**
         *  ERROR_NETWORK_TIMEOUT = 1;
         *  ERROR_NETWORK = 2;
         *  ERROR_AUDIO = 3;
         *  ERROR_SERVER = 4;
         *  ERROR_CLIENT = 5;
         *  ERROR_SPEECH_TIMEOUT = 6;
         *  ERROR_NO_MATCH = 7;
         *  ERROR_RECOGNIZER_BUSY = 8;
         *  ERROR_INSUFFICIENT_PERMISSIONS = 9;
         *
         * @param error code is defined in SpeechRecognizer
         */
        httpCallback.onSuccess(true, _voiceResults);
    }

    public interface HttpCallback {
        public void onSuccess(boolean response, String text);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        ArrayList data = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String word = (String) data.get(data.size() - 1);

        Log.i("TEST", "partial_results: " + word);
    }

    public void onEvent(int eventType, Bundle params) {
        Log.d(TAG, "onEvent " + eventType);
    }
}


