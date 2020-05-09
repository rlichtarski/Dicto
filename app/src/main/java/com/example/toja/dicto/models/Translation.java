package com.example.toja.dicto.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "translations")
public class Translation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "translation_id")
    @NonNull
    private int translationId;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("pronunciation")
    @Expose
    private Pronunciation pronunciation;
    @ColumnInfo(name = "timestamp")
    private int timestamp;

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

    public Translation(List<Result> results, String word,Pronunciation pronunciation,int timestamp) {
        this.results = results;
        this.word = word;
        this.pronunciation = pronunciation;
        this.timestamp = timestamp;
    }

    public Translation(int translationId,String word,List<Result> results,Pronunciation pronunciation) {
        this.translationId = translationId;
        this.word = word;
        this.results = results;
        this.pronunciation = pronunciation;
    }

    public Translation(int translationId,String word,List<Result> results,Pronunciation pronunciation,int timestamp) {
        this.translationId = translationId;
        this.word = word;
        this.results = results;
        this.pronunciation = pronunciation;
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
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

    public int getTranslationId() {
        return translationId;
    }

    public void setTranslationId(int translationId) {
        this.translationId = translationId;
    }
}
