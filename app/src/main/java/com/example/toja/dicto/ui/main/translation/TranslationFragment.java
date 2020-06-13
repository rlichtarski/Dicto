package com.example.toja.dicto.ui.main.translation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.toja.dicto.R;

import dagger.android.support.DaggerFragment;

public class TranslationFragment extends DaggerFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        Toast.makeText(getActivity(),"TranslationFragment",Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.layout_translation_list_item, container, false);
    }
}
