package com.example.toja.dicto.ui.main.history;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.TranslationResponse;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    private static View.OnClickListener mOnItemClickListener;

    TextView historyWord;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        historyWord = itemView.findViewById(R.id.word_history_item);

        itemView.setTag(this);
        itemView.setOnClickListener(mOnItemClickListener);
    }

    public void onBind(TranslationResponse translationResponse) {
        historyWord.setText(translationResponse.getWord());
    }

    public static void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
