package com.example.toja.dicto.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

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
    private List<String> examples = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param examples
     * @param synonyms
     * @param partOfSpeech
     * @param definition
     * @param similarTo
     */
    public Result(String definition, String partOfSpeech, List<String> synonyms, List<String> similarTo, List<String> examples) {
        super();
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.synonyms = synonyms;
        this.similarTo = similarTo;
        this.examples = examples;
    }

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

}
