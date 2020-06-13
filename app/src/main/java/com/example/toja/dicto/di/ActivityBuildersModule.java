package com.example.toja.dicto.di;

import com.example.toja.dicto.MainActivity;
import com.example.toja.dicto.di.main.MainFragmentBuildersModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    private static final String TAG = "ActivityBuildersModule";

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
