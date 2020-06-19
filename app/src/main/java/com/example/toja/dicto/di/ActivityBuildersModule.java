package com.example.toja.dicto.di;

import com.example.toja.dicto.MainActivity;
import com.example.toja.dicto.di.main.MainFragmentBuildersModule;
import com.example.toja.dicto.di.main.MainModule;
import com.example.toja.dicto.di.main.MainViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    private static final String TAG = "ActivityBuildersModule";

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
