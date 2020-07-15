package com.example.toja.dicto.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pronunciation {

    @SerializedName("all")
    @Expose
    private String pronunciation;

    public Pronunciation() {
    }

    public Pronunciation(String pronunciation) {
        super();
        this.pronunciation = pronunciation;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

}
