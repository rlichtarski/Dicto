package com.example.toja.dicto.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.repositories.TranslationRepository;
import com.example.toja.dicto.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TranslationViewModel extends ViewModel {

    private static final String TAG = "TranslationViewModel";

    private TranslationRepository translationRepository;
    private String word;

    @Inject
    public TranslationViewModel(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public Observable<Resource<List<Translation>>> getTranslation(String word) {
        this.word = word;
        return translationRepository.getTranslation(word);
    }

}
