package com.example.toja.dicto;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.ui.main.translation.TranslationFragment;
import com.example.toja.dicto.utils.Resource;
import com.example.toja.dicto.viewmodels.TranslationViewModel;
import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MainActivity";

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private TranslationViewModel translationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translationViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(TranslationViewModel.class);

        fetchData();
        testTranslationFragment();
    }

    private void testTranslationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new TranslationFragment())
                .commit();
    }

    private void fetchData() {
        disposables.add(translationViewModel.getTranslation("conceal")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::showResults));
    }

    private void showResults(Resource<List<Translation>> resource) {
        switch (resource.status) {
            case LOADING: {
                Log.d(TAG,"show: LOADING...");
                break;
            }

            case ERROR: {
                Log.e(TAG,"show: ERROR message: " + resource.message);
                break;
            }

            case SUCCESS: {
                for(int i=0; i<resource.data.size(); i++) {
                    Log.d(TAG,"show: SUCCESS: " + resource.data.get(i).getDefinition() + ", " + resource.message);
                }
            }
        }
    }
}
