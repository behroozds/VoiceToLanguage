package com.necode.voicetomylanguage.JsonData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageResponse {

    @SerializedName("sl")
    @Expose
    private Object sl;


    public Object getName() {
        return sl;
    }

    public void setName(Object name) {
        sl = name;
    }
}
