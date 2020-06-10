package com.example.toja.dicto.repositories;

import com.example.toja.dicto.models.Translation;
import com.example.toja.dicto.network.WordsApi;
import com.example.toja.dicto.persistance.TranslationsDao;
import com.example.toja.dicto.util.InstantExecutorExtension;
import com.example.toja.dicto.utils.Resource;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.reactivex.Single;

import static com.example.toja.dicto.repositories.TranslationRepository.INSERT_FAILURE;
import static com.example.toja.dicto.repositories.TranslationRepository.INSERT_SUCCESS;
import static com.example.toja.dicto.util.TestUtil.TEST_TRANSLATION_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(InstantExecutorExtension.class)
public class TranslationRepositoryTest {

    /*private TranslationsDao translationsDao;
    private TranslationRepository translationRepository;
    private WordsApi wordsApi;

    @BeforeEach
    public void initEach() {
        translationsDao = mock(TranslationsDao.class);
        wordsApi = mock(WordsApi.class);
        translationRepository = new TranslationRepository(translationsDao, wordsApi);
    }

    @Test
    public void insertTranslation_returnRow() throws Exception {
        //arrange
        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);

        when(translationsDao.insertTranslation(any(Translation.class))).thenReturn(returnedData);

        //Act
        final Resource<Integer> returnedValue = translationRepository
                .insertTranslation(TEST_TRANSLATION_1)
                .blockingFirst();

        //Assert
        verify(translationsDao).insertTranslation(any(Translation.class));
        verifyNoMoreInteractions(translationsDao);
        System.out.println("returned value: " + returnedValue.data);
        assertEquals(Resource.success(1,INSERT_SUCCESS),returnedValue);
    }

    @Test
    public void insertTranslation_returnFailure() throws Exception {
        // Arrange
        final Long failedRow = -1L;
        final Single<Long> returnedData = Single.just(failedRow);

        when(translationsDao.insertTranslation(any(Translation.class))).thenReturn(returnedData);

        //Act
        final Resource<Integer> returnedValue = translationRepository
                .insertTranslation(TEST_TRANSLATION_1)
                .blockingFirst();

        //Assert
        verify(translationsDao).insertTranslation(any(Translation.class));
        verifyNoMoreInteractions(translationsDao);
        assertEquals(Resource.error(INSERT_FAILURE,null),returnedValue);
    }*/
}
