package com.example.toja.dicto.ui.main.translation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.TranslationResponse;
import com.example.toja.dicto.ui.main.SharedViewModel;
import com.example.toja.dicto.utils.Resource;
import com.example.toja.dicto.utils.VerticalSpaceItemDecoration;
import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TranslationFragment extends DaggerFragment {

    private static final String TAG = "TranslationFragment";

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private TranslationViewModel translationViewModel;

    private SharedViewModel sharedViewModel;

    @Inject
    TranslationRecyclerAdapter translationRecyclerAdapter;

    @Inject
    VerticalSpaceItemDecoration verticalSpaceItemDecoration;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private SearchView mSearchView;
    private TextView mWordTxtView;
    private RecyclerView mTranslationRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translate,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        mTranslationRecyclerView = view.findViewById(R.id.translation_recycler_view);
        mWordTxtView = view.findViewById(R.id.word_txt_view);
        mSearchView = view.findViewById(R.id.search_view);

        translationViewModel = new ViewModelProvider(this,viewModelProviderFactory).get(TranslationViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity(),viewModelProviderFactory).get(SharedViewModel.class);

        subscribeSharedViewModel();
        initTranslationRecyclerView();
        initSearchView();
    }

    private void fetchData(String word) {
        disposables.add(translationViewModel.getTranslation(word)
                .distinct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showResults));
    }

    private void showResults(Resource<TranslationResponse> translationResponseResource) {
        switch (translationResponseResource.status) {
            case LOADING: {
                Log.d(TAG,"showResults: LOADING...");
                break;
            }

            case ERROR: {
                Log.e(TAG,"showResults ERROR message: " + translationResponseResource.message);
                break;
            }

            case SUCCESS: {
                for (int i = 0; i < translationResponseResource.data.getTranslations().size(); i++) {
                    Log.d(TAG,"showResults: SUCCESS: " + translationResponseResource.data.getTranslations().get(i).getDefinition() + ", " + translationResponseResource.message);
                }
                setWidgets(translationResponseResource.data);
            }
        }
    }

    private void subscribeSharedViewModel() {
        sharedViewModel.getTranslationState().observe(getActivity(),translationState -> {
            switch (translationState) {
                case FROM_HISTORY: {
                    sharedViewModel.getSelectedTranslationItem().observe(getActivity(),this::setWidgets);
                    break;
                }

                default: {
                    //nothing
                    break;
                }
            }
        });
    }

    private void setWidgets(TranslationResponse translation) {
        mWordTxtView.setText(translation.getWord());
        translationRecyclerAdapter.setTranslationList(translation.getTranslations());
    }

    private void initTranslationRecyclerView() {
        mTranslationRecyclerView.addItemDecoration(verticalSpaceItemDecoration);
        mTranslationRecyclerView.setLayoutManager(linearLayoutManager);
        mTranslationRecyclerView.setAdapter(translationRecyclerAdapter);
    }

    private void initSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
