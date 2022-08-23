package com.necode.voicetomylanguage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.necode.voicetomylanguage.App.CustomSpeechRecognizer;
import com.necode.voicetomylanguage.App.CustomToastTranslate;
import com.necode.voicetomylanguage.App.FunctionHelper;
import com.necode.voicetomylanguage.App.GoogleTranslate;
import com.necode.voicetomylanguage.App.GsonResponse;
import com.necode.voicetomylanguage.App.RetrofitResponse;
import com.necode.voicetomylanguage.App.VolleyResponse;
import com.necode.voicetomylanguage.JsonData.LanguageResponse;
import com.necode.voicetomylanguage.JsonData.TranslateResponse;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    SpeechRecognizer recognizer;
    ImageView Start;
    SearchableSpinner TargetSpinner, FromSpinner;
    List<String> Nomads = new ArrayList<>(), Languages = new ArrayList<>();
    int MYLanguage = 0, TargetLanguage = 0;
    TextView Text, TargetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**  Variables   **/
        Start = findViewById(R.id.imageFilterView);
        FromSpinner = findViewById(R.id.spinner_search_from);
        TargetSpinner = findViewById(R.id.spinner_search_target);
        Text = findViewById(R.id.editTextTextPersonName3);
        TargetText = findViewById(R.id.editTextTextPersonName2);
        /** End Variables   **/
        Text.setMovementMethod(new ScrollingMovementMethod());
        ///GetLanguages

        GetLanguage();


        Listeners();


    }

    private void GetLanguage() {
        String languages = FunctionHelper.readRawResource(R.raw.languages, MainActivity.this);
        LanguageResponse new_res_json = new Gson().fromJson(languages, LanguageResponse.class);
        CovertData(new_res_json.getName().toString());
        SetLanguage();
    }

    private void CovertData(String datas) {
        datas = datas.replace("{", "").replace("}", "");
        String[] arrays = datas.split(",");
        for (int i = 1; i < arrays.length; i++) {
            String[] item = arrays[i].split("=");
            if (MYLanguage == 0) {
                if (item[0].trim().toString().equals("fa")) {
                    MYLanguage = i - 1;
                } else if (item[0].trim().toString().equals("en")) {
                    TargetLanguage = i - 1;
                }
            }
            Nomads.add(item[0]);
            Languages.add(item[1]);
        }
    }

    private void SetLanguage() {

        FromSpinner.setTitle("select your language/زبان خود را انتخاب کنید");
        FromSpinner.setPositiveButton("ok/تایید");
        TargetSpinner.setTitle("select your language/زبان خود را انتخاب کنید");
        TargetSpinner.setPositiveButton("ok/تایید");
        ///
        ArrayAdapter<String> AdapterLanguages = new ArrayAdapter<String>(this, R.layout.item_spinner, Languages);
        AdapterLanguages.setDropDownViewResource(R.layout.item_spinner);
        ///
        FromSpinner.setAdapter(AdapterLanguages);
        FromSpinner.setSelection(MYLanguage);
        FromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ChangeFromLanguage(i);
                } catch (Exception e) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        TargetSpinner.setAdapter(AdapterLanguages);
        TargetSpinner.setSelection(TargetLanguage);
        TargetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ChangeTargetLanguage(i);
                } catch (Exception e) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Start.setVisibility(View.VISIBLE);


    }

    private void ChangeTargetLanguage(int i) {
        Text.setText("");

        TargetLanguage = i;
    }

    private void ChangeFromLanguage(int i) {
        Text.setText("");
        MYLanguage = i;
    }

    private void Listeners() {
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (FunctionHelper.MicroPhone(MainActivity.this)) {

                    try {
                        if (recognizer != null) {
                            recognizer.stopListening();
                            recognizer.cancel();
                            recognizer.destroy();
                            recognizer = null;
                            Start.setImageResource(R.drawable.ic_microphone);
                        } else {
                            Start.setImageResource(R.drawable.ic_stop);
                            StartSpeech();
                        }
                    } catch (Exception e) {
                    }


                } else {
                    Toast.makeText(MainActivity.this, "اجازه دسترسی به میکروفون داده شود!", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void StartSpeech() {
        recognizer = SpeechRecognizer
                .createSpeechRecognizer(this.getApplicationContext());

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                "com.domain.app");
        intent.putExtra("android.speech.extra.DICTATION_MODE", true);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 6);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "");
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 7000);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Nomads.get(MYLanguage).trim());

        Log.wtf("dsdsadsdsdsadsdsasdsdsdds", Nomads.get(MYLanguage) + "");
        recognizer.setRecognitionListener(new CustomSpeechRecognizer(Nomads.get(MYLanguage).trim(), Nomads.get(TargetLanguage).trim(), MainActivity.this, new CustomSpeechRecognizer.HttpCallback() {
            @Override
            public void onSuccess(boolean response, String text) {
                try {
                    if (response) {
                        recognizer.startListening(intent);

                        if (text.toString().length() > 0) {
                            try {
                                Text.append(Html.fromHtml("<br>" + text));
                                new GoogleTranslate(MainActivity.this).Translate(text,
                                        Nomads.get(TargetLanguage), Nomads.get(MYLanguage), new GoogleTranslate.HttpCallback() {
                                            @Override
                                            public void onSuccess(String response) {
                                                TargetText.setText(Html.fromHtml("<br>" + response));
                                            }
                                        });

                            } catch (Exception e) {
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }));
        recognizer.startListening(intent);
    }

    private void ShowToast(String response) {
        CustomToastTranslate toastTranslate = new CustomToastTranslate(MainActivity.this);
        toastTranslate.show(response);
    }
}