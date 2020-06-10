package com.example.toja.dicto.network;

import com.example.toja.dicto.models.TranslationResponse;

import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

@Singleton
public interface WordsApi {

    @GET("words/{word}")
    Single<TranslationResponse> getWordTranslation(@Path("word") String word);

}
