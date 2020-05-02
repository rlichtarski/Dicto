package com.example.toja.dicto.network;

import com.example.toja.dicto.models.Translation;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WordsApi {

    @GET("words/{word}")
    Flowable<Translation> getWordTranslation(@Path("word") String word);

}
