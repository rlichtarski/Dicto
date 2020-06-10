package com.example.toja.dicto.persistance;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.toja.dicto.models.Translation;

@Database(entities = {Translation.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class TranslationDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "translations_db";

    public abstract TranslationsDao getTranslationsDao();
}
