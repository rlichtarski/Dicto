package com.example.toja.dicto.di.main;

import androidx.lifecycle.ViewModel;

import com.example.toja.dicto.di.ViewModelKey;
import com.example.toja.dicto.ui.main.history.HistoryViewModel;
import com.example.toja.dicto.ui.main.translation.TranslationViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TranslationViewModel.class)
    public abstract ViewModel bindTranslationViewModel(TranslationViewModel translationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel.class)
    public abstract ViewModel bindHistoryViewModel(HistoryViewModel historyViewModel);

}
