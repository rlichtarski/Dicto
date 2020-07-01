package com.example.toja.dicto.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.toja.dicto.persistance.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity(tableName = "translations", indices = @Index(value = {"word"}, unique = true))
public class TranslationResponse {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id")
    @NonNull
    private int wordId;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("results")
    @Expose
    private List<Translation> translations = null;
    @SerializedName("pronunciation")
    @Expose
    @Embedded
    private Pronunciation pronunciation;
    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    @TypeConverters(Converters.class)
    private Date createdAt;

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

    public TranslationResponse(int wordId,String word,List<Translation> translations,Pronunciation pronunciation,Date createdAt) {
        this.wordId = wordId;
        this.word = word;
        this.translations = translations;
        this.pronunciation = pronunciation;
        this.createdAt = createdAt;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
