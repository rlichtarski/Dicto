package com.example.toja.dicto.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toja.dicto.models.Translation;

import io.reactivex.Single;

@Dao
public interface TranslationsDao {

    @Insert
    Single<Long> insertTranslation(Translation translation) throws Exception;

    @Update
    Single<Integer> updateTranslation() throws Exception;

    @Query("DELETE FROM translations WHERE word = :word")
    Single<Integer> deleteTranslation(String word) throws Exception;

    @Query("SELECT * FROM translations")
    LiveData<Translation> getAllTranslations();

    @Query("SELECT word FROM translations")
    LiveData<String> getAllTranslationsWords();

}
