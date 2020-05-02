package com.example.toja.dicto.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.toja.dicto.utils.Constants.WORDS_API_BASE_URL;

@Module
public abstract class AppModule {

    private static final String TAG = "AppModule";

    @Singleton
    @Provides
    private static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(WORDS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
