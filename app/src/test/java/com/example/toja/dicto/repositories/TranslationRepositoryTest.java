package com.example.toja.dicto.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.toja.dicto.network.WordsApi;
import com.example.toja.dicto.persistance.TranslationsDao;
import com.example.toja.dicto.util.InstantExecutorExtension;
import com.example.toja.dicto.util.LiveDataTestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static com.example.toja.dicto.util.TestUtil.TEST_WORDS_STRING_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(InstantExecutorExtension.class)
public class TranslationRepositoryTest {

    private TranslationsDao translationsDao;
    private TranslationRepository translationRepository;
    private WordsApi wordsApi;

    @BeforeEach
    public void initEach() {
        translationsDao = mock(TranslationsDao.class);
        wordsApi = mock(WordsApi.class);
        translationRepository = new TranslationRepository(translationsDao, wordsApi);
    }

    /*
        retrieve notes
        return list of notes
     */
    /*@Test
    void getWordsList_returnListWithWords() throws Exception {
        // Arrange
        List<String> words = TEST_WORDS_STRING_LIST;
        LiveDataTestUtil<List<String>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<String>> returnedData = new MutableLiveData<>();
        returnedData.setValue(words);
        when(translationsDao.getAllWordsFromTranslations()).thenReturn(returnedData);

        //Act
        List<String> observedData = liveDataTestUtil.getValue(translationRepository.getAllWordsFromTranslations());

        //Assert
        assertEquals(words, observedData);
    }

    *//*
        retrieve notes
        return empty list
     *//*
    @Test
    void getNotes_returnEmptyList() throws Exception {
        // Arrange
        List<String> words = new ArrayList<>();
        LiveDataTestUtil<List<String>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<String>> returnedData = new MutableLiveData<>();
        returnedData.setValue(words);
        when(translationsDao.getAllWordsFromTranslations()).thenReturn(returnedData);

        //Act
        List<String> observedData = liveDataTestUtil.getValue(translationRepository.getAllWordsFromTranslations());

        //Assert
        assertEquals(words, observedData);
    }*/

}
