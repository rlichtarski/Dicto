package com.example.toja.dicto.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProvider(ViewModelProviderFactory viewModelProviderFactory);

}
