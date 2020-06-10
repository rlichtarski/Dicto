package com.example.toja.dicto.persistance;

import androidx.room.TypeConverter;

import com.example.toja.dicto.models.Translation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String[] fromString(String value) {
        Type listType = new TypeToken<String[]>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(String[] list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<String> fromStringToList(String data) {
        Gson gson = new Gson();

        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String fromListToString(List<String> results) {
        Gson gson = new Gson();
        return gson.toJson(results);
    }

    @TypeConverter
    public static List<Translation> stringToTranslationList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Translation>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String translationListToString(List<Translation> translationList) {
        Gson gson = new Gson();
        return gson.toJson(translationList);
    }

}
