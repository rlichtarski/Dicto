package com.example.toja.dicto.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.toja.dicto.models.Translation;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface TranslationsDao {

    @Insert
    Single<Long> insertTranslation(Translation translation) throws Exception;

    @Insert(onConflict = IGNORE)
    Completable insertTranslationResponse(List<Translation> translations) throws Exception;

    /*@Update
    Single<Integer> updateTranslation() throws Exception;*/

    @Query("DELETE FROM translations WHERE word = :word")
    Single<Integer> deleteTranslation(String word) throws Exception;

    @Query("SELECT * FROM translations")
    Flowable<List<Translation>> getAllTranslations();

    @Query("SELECT * FROM translations WHERE word = :word")
    Single<List<Translation>> getTranslationsForWord(String word);

    @Query("SELECT word FROM translations")
    LiveData<List<String>> getAllTranslationsWordsList();

}
