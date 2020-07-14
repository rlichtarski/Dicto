package com.example.toja.dicto.ui.main.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.TranslationResponse;
import com.example.toja.dicto.ui.main.SharedViewModel;
import com.example.toja.dicto.utils.VerticalSpaceItemDecoration;
import com.example.toja.dicto.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HistoryFragment extends DaggerFragment {

    private static final String TAG = "HistoryFragment";

    private HistoryViewModel historyViewModel;

    private SharedViewModel sharedViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    HistoryRecyclerAdapter historyRecyclerAdapter;

    @Inject
    VerticalSpaceItemDecoration verticalSpaceItemDecoration;

    private RecyclerView mHistoryRecyclerView;

    private TextView mLongPressDeleteInfo;

    private List<TranslationResponse> translationResponseList;

    private View.OnClickListener onItemClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        TranslationResponse translationResponse = translationResponseList.get(position);
        sharedViewModel.selectTranslationItem(translationResponse);
        sharedViewModel.setTranslationStateFromHistory();
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.translationScreen);
    };

    private View.OnLongClickListener onItemLongClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        TranslationResponse translationResponse = translationResponseList.get(position);
        String word = translationResponse.getWord();
        int wordId = translationResponse.getWordId();

        new AlertDialog.Builder(getContext())
                .setMessage(getString(R.string.delete_word_q, word))
                .setPositiveButton(R.string.delete,(dialogInterface,i) -> {
                    historyViewModel.deleteTranslation(wordId);
                    historyRecyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), getString(R.string.word_deleted_info, word),Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.discard,(dialogInterface,i) -> {
                    //do nothing
                })
                .show();
        return true;
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        mHistoryRecyclerView = view.findViewById(R.id.history_recycler_view);
        mLongPressDeleteInfo = view.findViewById(R.id.long_press_delete_info);

        historyViewModel = new ViewModelProvider(this,viewModelProviderFactory).get(HistoryViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity(),viewModelProviderFactory).get(SharedViewModel.class);

        translationResponseList = new ArrayList<>();

        initHistoryRecyclerView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeObservers();
    }

    public void subscribeObservers() {
        historyViewModel.observeTranslations().removeObservers(getViewLifecycleOwner());
        historyViewModel.observeTranslations().observe(getViewLifecycleOwner(),translations -> {
            translationResponseList = translations;
            historyRecyclerAdapter.setTranslationResponseList(translations);
            if(translationResponseList.size() > 0) {
                mLongPressDeleteInfo.setVisibility(View.VISIBLE);
            } else {
                mLongPressDeleteInfo.setVisibility(View.GONE);            }
        });
    }

    private void initHistoryRecyclerView() {
        mHistoryRecyclerView.addItemDecoration(verticalSpaceItemDecoration);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHistoryRecyclerView.setAdapter(historyRecyclerAdapter);
        historyRecyclerAdapter.setOnItemClickListener(onItemClickListener);
        historyRecyclerAdapter.setOnItemLongClickListener(onItemLongClickListener);
    }

}
