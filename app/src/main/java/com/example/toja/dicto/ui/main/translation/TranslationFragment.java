package com.example.toja.dicto.ui.main.translation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.MainActivity;
import com.example.toja.dicto.R;
import com.example.toja.dicto.models.TranslationResponse;
import com.example.toja.dicto.ui.main.SharedViewModel;
import com.example.toja.dicto.utils.Resource;
import com.example.toja.dicto.utils.VerticalSpaceItemDecoration;
import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
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

    private TextView mWordTxtView;
    private RecyclerView mTranslationRecyclerView;

    private boolean isFromHistory = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translate,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        mTranslationRecyclerView = view.findViewById(R.id.translation_recycler_view);
        mWordTxtView = view.findViewById(R.id.word_txt_view);

        translationViewModel = new ViewModelProvider(this,viewModelProviderFactory).get(TranslationViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity(),viewModelProviderFactory).get(SharedViewModel.class);

        subscribeSharedViewModel();
        initTranslationRecyclerView();
    }

    private void fetchData(String word) {
        disposables.add(translationViewModel.getTranslation(word)
                .distinct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showResults,throwable ->  Log.e(TAG, "ERROR")));
    }

    private void showResults(Resource<TranslationResponse> translationResponseResource) {
        switch (translationResponseResource.status) {
            case LOADING: {
                Log.d(TAG,"showResults: LOADING...");
                break;
            }

            case ERROR: {
                Log.e(TAG,"showResults ERROR message: " + translationResponseResource.message);
                mWordTxtView.setText(getString(R.string.word_not_found));
                translationRecyclerAdapter.clearTranslationList();
                Toast.makeText(getContext(),getString(R.string.word_not_found),Toast.LENGTH_SHORT).show();
                break;
            }

            case SUCCESS: {
                sharedViewModel.selectTranslationItem(translationResponseResource.data);
                setWidgets(translationResponseResource.data);
            }
        }
    }

    private void subscribeSharedViewModel() {
        sharedViewModel.getTranslationState().observe(getViewLifecycleOwner(),translationState -> {
            switch (translationState) {
                case FROM_HISTORY: {
                    sharedViewModel.getSelectedTranslationItem().observe(getViewLifecycleOwner(),this::setWidgets);
                    isFromHistory = true;
                    sharedViewModel.setTranslationStateMain();
                    break;
                }

                case FROM_MAIN: {
                    isFromHistory = false;
                    break;
                }
            }
        });
    }

    private void setWidgets(TranslationResponse translation) {
        if(translation != null) {
            if(translation.getWord() != null && translation.getTranslations() != null) {
                mWordTxtView.setText(translation.getWord());
                translationRecyclerAdapter.setTranslationList(translation.getTranslations());
            }
        } else {
            mWordTxtView.setText("");
            translationRecyclerAdapter.clearTranslationList();
        }
    }

    private void initTranslationRecyclerView() {
        mTranslationRecyclerView.addItemDecoration(verticalSpaceItemDecoration);
        mTranslationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTranslationRecyclerView.setAdapter(translationRecyclerAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu,@NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setActionView(searchView);
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchData(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(isFromHistory) {
            sharedViewModel.selectTranslationItem(null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
