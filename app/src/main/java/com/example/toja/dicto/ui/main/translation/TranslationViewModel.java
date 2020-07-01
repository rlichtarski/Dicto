package com.example.toja.dicto.ui.main.translation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.models.TranslationResponse;
import com.example.toja.dicto.repositories.TranslationRepository;
import com.example.toja.dicto.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TranslationViewModel extends ViewModel {

    private static final String TAG = "TranslationViewModel";

    private TranslationRepository translationRepository;

    @Inject
    public TranslationViewModel(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public Observable<Resource<TranslationResponse>> getTranslation(String word) {
        return translationRepository.getTranslation(word);
    }

}
