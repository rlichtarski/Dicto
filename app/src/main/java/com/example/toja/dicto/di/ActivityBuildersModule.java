package com.example.toja.dicto.di;

import com.example.toja.dicto.MainActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    private static final String TAG = "ActivityBuildersModule";

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
