package com.example.toja.dicto.ui.main.history;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.TranslationResponse;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    TextView historyWord;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        historyWord = itemView.findViewById(R.id.word_history_item);
    }

    public void onBind(TranslationResponse translationResponse) {
        historyWord.setText(translationResponse.getWord());
    }
}
