package com.example.toja.dicto.ui.main.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.ui.main.translation.TranslationViewModel;
import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HistoryFragment extends DaggerFragment {

    private static final String TAG = "HistoryFragment";

    private HistoryViewModel historyViewModel;

    @Inject
    ViewModelProviderFactory verticalSpaceItemDecoration;

    TextView words;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        words = view.findViewById(R.id.words_txt);

        historyViewModel = new ViewModelProvider(this,verticalSpaceItemDecoration).get(HistoryViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        subscribeObservers();
    }

    public void subscribeObservers() {
        historyViewModel.observeTranslations().observe(this,translations -> {
            for(Translation translation : translations) {
                words.append(translation.getWord() + "\n");
            }
        });

    }


}
