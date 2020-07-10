package com.example.toja.dicto.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.models.TranslationResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface TranslationsDao {

    @Insert(onConflict = IGNORE)
    Completable insertTranslationResponse(TranslationResponse translationResponse) throws Exception;

    @Query("DELETE FROM translations WHERE word = :word")
    Single<Integer> deleteTranslation(String word) throws Exception;

    @Query("SELECT * FROM translations")
    LiveData<List<TranslationResponse>> getAllTranslations();

    @Query("SELECT * FROM translations WHERE word = :word OR word = :refactoredWord")
    Single<TranslationResponse> getTranslationsForWord(String word, String refactoredWord);

}
