package com.example.toja.dicto.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TranslationResponse {

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("results")
    @Expose
    private List<Translation> translations = null;
    @SerializedName("pronunciation")
    @Expose
    private Pronunciation pronunciation;

    /**
     * No args constructor for use in serialization
     *
     */
    public TranslationResponse() {
    }

    /**
     *
     * @param pronunciation
     * @param word
     * @param translations
     */
    public TranslationResponse(String word,List<Translation> translations,Pronunciation pronunciation) {
        super();
        this.word = word;
        this.translations = translations;
        this.pronunciation = pronunciation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public Pronunciation getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(Pronunciation pronunciation) {
        this.pronunciation = pronunciation;
    }

}
