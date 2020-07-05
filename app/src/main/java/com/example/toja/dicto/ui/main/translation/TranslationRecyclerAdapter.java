package com.example.toja.dicto.ui.main.translation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.Translation;

import java.util.ArrayList;
import java.util.List;

public class TranslationRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Translation> translationList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_translation_list_item,parent,false);
        return new TranslationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,int position) {
        ((TranslationViewHolder) holder).onBind(translationList.get(position));
    }

    @Override
    public int getItemCount() {
        if (translationList != null) {
            return translationList.size();
        }
        return 0;
    }

    public void setTranslationList(List<Translation> translationList) {
        if (translationList != null) {
            this.translationList = translationList;
            notifyDataSetChanged();
        }
    }

    public void clearTranslationList() {
        if(translationList != null) {
            int translationSize = translationList.size();
            translationList.clear();
            notifyItemRangeRemoved(0, translationSize);
        }
    }

}
