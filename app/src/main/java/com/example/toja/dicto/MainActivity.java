package com.example.toja.dicto;

import android.os.Bundle;

import com.example.toja.dicto.ui.main.history.HistoryFragment;
import com.example.toja.dicto.ui.main.translation.TranslationFragment;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTranslationFragment();
    }

    private void testTranslationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new HistoryFragment())
                .commit();
    }

}
