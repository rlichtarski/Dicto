package com.example.toja.dicto.ui.main.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.models.TranslationResponse;
import com.example.toja.dicto.repositories.TranslationRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HistoryViewModel extends ViewModel {

    private static final String TAG = "HistoryViewModel";

    private TranslationRepository translationRepository;

    @Inject
    public HistoryViewModel(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public LiveData<List<TranslationResponse>> observeTranslations() {
        return translationRepository.getAllTranslations();
    }

    public void deleteTranslation(int wordId) {
        translationRepository.deleteTranslation(wordId);
    }
}
