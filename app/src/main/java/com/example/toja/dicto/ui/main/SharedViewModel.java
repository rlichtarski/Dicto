package com.example.toja.dicto.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.toja.dicto.models.TranslationResponse;

import javax.inject.Inject;

public class SharedViewModel extends ViewModel {

    public enum TranslationState {FROM_HISTORY, FROM_MAIN}

    @Inject
    public SharedViewModel() {
    }

    private final MutableLiveData<TranslationResponse> selectedTranslationItem = new MutableLiveData<>();
    private final MutableLiveData<TranslationState> translationState = new MutableLiveData<>();

    public void selectTranslationItem(TranslationResponse translationResponse) {
        selectedTranslationItem.setValue(translationResponse);
    }

    public LiveData<TranslationResponse> getSelectedTranslationItem() {
        return selectedTranslationItem;
    }

    public void setTranslationStateMain() {
        translationState.setValue(TranslationState.FROM_MAIN);
    }

    public void setTranslationStateFromHistory() {
        translationState.setValue(TranslationState.FROM_HISTORY);
    }

    public LiveData<TranslationState> getTranslationState() {
        return translationState;
    }

}
