package com.example.toja.dicto.persistance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.toja.dicto.models.Translation;

@Database(entities = {Translation.class}, version = 1)
public abstract class TranslationDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "translations_db";

    public abstract TranslationsDao getTranslationsDao();
}
