package com.example.toja.dicto.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.toja.dicto.models.TranslationResponse;
import com.example.toja.dicto.network.WordsApi;
import com.example.toja.dicto.persistance.TranslationsDao;
import com.example.toja.dicto.utils.NetworkBoundResource;
import com.example.toja.dicto.utils.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class TranslationRepository {

    private static final String TAG = "TranslationRepository";

    private MediatorLiveData<List<TranslationResponse>> mTranslationResponse = new MediatorLiveData<>();

    private final TranslationsDao translationsDao;
    private final WordsApi wordsApi;

    @Inject
    public TranslationRepository(TranslationsDao translationsDao, WordsApi wordsApi) {
        this.translationsDao = translationsDao;
        this.wordsApi = wordsApi;
    }

    public Observable<Resource<TranslationResponse>> getTranslation(String word) {
        return Observable.create(emitter -> new NetworkBoundResource<TranslationResponse>(emitter) {
            @Override
            protected Single<TranslationResponse> createCall() {
                return wordsApi.getWordTranslation(word);
            }

            @Override
            protected Completable saveCallResult(TranslationResponse response) {
                try {
                    return translationsDao.insertTranslationResponse(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected Single<TranslationResponse> loadFromDb() {
                StringBuilder wordBuilder = new StringBuilder();

                if(word.endsWith("s")) {
                    wordBuilder = wordBuilder.append(word.substring(0, word.length()-1));
                }

                return translationsDao.getTranslationsForWord(word.toLowerCase(), String.valueOf(wordBuilder));
            }
        });
    }

    public LiveData<List<TranslationResponse>> getAllTranslations() {
        LiveData<List<TranslationResponse>> source = translationsDao.getAllTranslations();
        mTranslationResponse.addSource(source,translationResponses -> mTranslationResponse.setValue(translationResponses));
        return mTranslationResponse;
    }

}
