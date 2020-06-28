package com.example.toja.dicto.ui.main.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.TranslationResponse;

import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TranslationResponse> translationResponseList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_history_list_item,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,int position) {
        ((HistoryViewHolder) holder).onBind(translationResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        if (translationResponseList != null) {
            return translationResponseList.size();
        }
        return 0;
    }

    public void setTranslationResponseList(List<TranslationResponse> translationResponseList) {
        if (translationResponseList != null) {
            this.translationResponseList = translationResponseList;
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        HistoryViewHolder.setOnItemClickListener(itemClickListener);
    }

}
