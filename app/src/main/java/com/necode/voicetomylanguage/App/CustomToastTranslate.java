package com.necode.voicetomylanguage.App;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.necode.voicetomylanguage.R;

public class CustomToastTranslate {

    public FragmentActivity activity;

    public CustomToastTranslate(FragmentActivity activity) {

        this.activity = activity;
    }

    public void show(String TextString) {
        //Creating the LayoutInflater instance
        LayoutInflater li = activity.getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        View layout = li.inflate(R.layout.toast_translate, activity.findViewById(R.id.custom_toast_layout));
        TextView Text = layout.findViewById(R.id.textView2);
        Text.setText(TextString);
        //Creating the Toast object
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }


}
