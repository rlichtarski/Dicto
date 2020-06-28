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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.TranslationResponse;
import com.example.toja.dicto.ui.main.translation.TranslationRecyclerAdapter;
import com.example.toja.dicto.utils.VerticalSpaceItemDecoration;
import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HistoryFragment extends DaggerFragment {

    private static final String TAG = "HistoryFragment";

    private HistoryViewModel historyViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    HistoryRecyclerAdapter historyRecyclerAdapter;

    @Inject
    VerticalSpaceItemDecoration verticalSpaceItemDecoration;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private RecyclerView mHistoryRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        mHistoryRecyclerView = view.findViewById(R.id.history_recycler_view);

        historyViewModel = new ViewModelProvider(this,viewModelProviderFactory).get(HistoryViewModel.class);

        initHistoryRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        subscribeObservers();
    }

    public void subscribeObservers() {
        historyViewModel.observeTranslations().observe(this,translations -> {
            historyRecyclerAdapter.setTranslationResponseList(translations);
        });

        historyViewModel.observeTranslations().observe(this,new Observer<List<TranslationResponse>>() {
            @Override
            public void onChanged(List<TranslationResponse> translationResponses) {

            }
        });
    }

    private void initHistoryRecyclerView() {
        mHistoryRecyclerView.addItemDecoration(verticalSpaceItemDecoration);
        mHistoryRecyclerView.setLayoutManager(linearLayoutManager);
        mHistoryRecyclerView.setAdapter(historyRecyclerAdapter);
    }

}
