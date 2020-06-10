package com.example.toja.dicto.repositories;

import com.example.toja.dicto.models.Translation;
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

    public static final String INSERT_SUCCESS = "Insert success";
    public static final String INSERT_FAILURE = "Insert failure";

    private final TranslationsDao translationsDao;
    private final WordsApi wordsApi;

    @Inject
    public TranslationRepository(TranslationsDao translationsDao, WordsApi wordsApi) {
        this.translationsDao = translationsDao;
        this.wordsApi = wordsApi;
    }

    public Observable<Resource<List<Translation>>> getTranslation(String word) {
        return Observable.create(emitter -> new NetworkBoundResource<List<Translation>, TranslationResponse>(emitter) {
            @Override
            protected Single<TranslationResponse> createCall() {
                return wordsApi.getWordTranslation(word);
            }

            @Override
            protected Completable saveCallResult(TranslationResponse response) {
                try {
                    for(int i=0; i<response.getTranslations().size(); i++) {
                        response.getTranslations().get(i).setWord(word);
                        response.getTranslations().get(i).setTimestamp((int) (System.currentTimeMillis() / 1000));
                    }
                    return translationsDao.insertTranslationResponse(response.getTranslations());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected Single<List<Translation>> loadFromDb() {
                return translationsDao.getTranslationsForWord(word);
            }
        });
    }
}
