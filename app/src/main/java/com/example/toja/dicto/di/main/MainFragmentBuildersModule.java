package com.example.toja.dicto.di.main;

import com.example.toja.dicto.ui.main.history.HistoryFragment;
import com.example.toja.dicto.ui.main.translation.TranslationFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TranslationFragment contributeTranslationFragment();

    @ContributesAndroidInjector
    abstract HistoryFragment contributeHistoryFragment();
}
