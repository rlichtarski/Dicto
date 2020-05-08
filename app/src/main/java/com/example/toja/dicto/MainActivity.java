package com.example.toja.dicto;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.network.WordsApi;
import com.example.toja.dicto.utils.Resource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    WordsApi wordsApi;

    private MediatorLiveData<Resource<Translation>> translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkApi().observe(this,translationResource -> {
            if (translationResource != null)
                switch (translationResource.status) {
                    case SUCCESS: {
                        Log.d(TAG,"onChanged: " + translationResource.data.getWord());
                    }
                }
        });
    }

    LiveData<Resource<Translation>> checkApi() {
        if(translation == null) {
            translation = new MediatorLiveData<>();
            translation.setValue(Resource.loading(null));
        }

        LiveData<Resource<Translation>> source = LiveDataReactiveStreams.fromPublisher(wordsApi.getWordTranslation("car")
                .map((Function<Translation, Resource<Translation>>) translation -> {
                    if(translation != null) {
                        return Resource.success(translation);
                    }
                    return Resource.error("Something went wrong", null);
                })
                .subscribeOn(Schedulers.io())
        );
        translation.addSource(source,translationResource -> {
            translation.setValue(translationResource);
            translation.removeSource(source);
        });

        return translation;
    }

}
