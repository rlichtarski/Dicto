package com.example.toja.dicto.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Translation {

    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("pronunciation")
    @Expose
    private Pronunciation pronunciation;

    /**
     * No args constructor for use in serialization
     *
     */
    public Translation() {
    }

    /**
     *
     * @param pronunciation
     * @param word
     * @param results
     */
    public Translation(String word,List<Result> results,Pronunciation pronunciation) {
        super();
        this.word = word;
        this.results = results;
        this.pronunciation = pronunciation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Pronunciation getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(Pronunciation pronunciation) {
        this.pronunciation = pronunciation;
    }

}
