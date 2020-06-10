package com.example.toja.dicto.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.toja.dicto.viewmodels.TranslationViewModel;
import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProvider(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(TranslationViewModel.class)
    public abstract ViewModel bindTranslationViewModel(TranslationViewModel translationViewModel);
}
