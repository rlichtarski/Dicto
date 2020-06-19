package com.example.toja.dicto.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "translations", indices = @Index(value = {"definition"}, unique = true))
public class Translation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id")
    @NonNull
    private int wordId;
    /*@Embedded
    private Pronunciation pronunciation;*/
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("partOfSpeech")
    @Expose
    private String partOfSpeech;
    @SerializedName("synonyms")
    @Expose
    private List<String> synonyms = null;
    @SerializedName("similarTo")
    @Expose
    private List<String> similarTo = null;
    @SerializedName("examples")
    @Expose
    private List<String> examples;
    @Expose
    private String word;
    @ColumnInfo(name = "timestamp")
    private int timestamp;

    /*public Translation(int wordId,Pronunciation pronunciation,String definition,String partOfSpeech,List<String> synonyms,List<String> similarTo,List<String> examples,String word,int timestamp) {
        this.wordId = wordId;
        this.pronunciation = pronunciation;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.synonyms = synonyms;
        this.similarTo = similarTo;
        this.examples = examples;
        this.word = word;
        this.timestamp = timestamp;
    }*/

    public Translation(int wordId,String definition,String partOfSpeech,List<String> synonyms,List<String> similarTo,List<String> examples,String word,int timestamp) {
        this.wordId = wordId;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.synonyms = synonyms;
        this.similarTo = similarTo;
        this.examples = examples;
        this.word = word;
        this.timestamp = timestamp;
    }

    /*public Translation(int wordId,String definition,String partOfSpeech,String word,int timestamp) {
        this.wordId = wordId;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.word = word;
        this.timestamp = timestamp;
    }*/

    public Translation(){}

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    /*public Pronunciation getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(Pronunciation pronunciation) {
        this.pronunciation = pronunciation;
    }*/

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getSimilarTo() {
        return similarTo;
    }

    public void setSimilarTo(List<String> similarTo) {
        this.similarTo = similarTo;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
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
}
