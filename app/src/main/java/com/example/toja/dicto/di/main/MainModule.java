package com.example.toja.dicto.di.main;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.toja.dicto.network.WordsApi;
import com.example.toja.dicto.persistance.TranslationDatabase;
import com.example.toja.dicto.persistance.TranslationsDao;
import com.example.toja.dicto.repositories.TranslationRepository;
import com.example.toja.dicto.ui.main.translation.TranslationRecyclerAdapter;
import com.example.toja.dicto.utils.VerticalSpaceItemDecoration;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class MainModule {

    @Provides
    static TranslationRecyclerAdapter provideTranslationRecyclerAdapter() {
        return new TranslationRecyclerAdapter();
    }

    @Provides
    static VerticalSpaceItemDecoration provideVerticalSpaceItemDecoration() {
        return new VerticalSpaceItemDecoration(30);
    }

    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application);
    }

    @Provides
    static WordsApi provideWordsApi(Retrofit retrofit) {
        return retrofit.create(WordsApi.class);
    }

    @Provides
    static TranslationsDao provideTranslationsDao(TranslationDatabase translationDatabase) {
        return translationDatabase.getTranslationsDao();
    }

    @Provides
    static TranslationRepository provideTranslationRepository(TranslationsDao translationsDao,WordsApi wordsApi) {
        return new TranslationRepository(translationsDao, wordsApi);
    }

}