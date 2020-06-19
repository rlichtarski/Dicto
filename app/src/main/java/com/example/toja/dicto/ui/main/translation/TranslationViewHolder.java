package com.example.toja.dicto.ui.main.translation;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toja.dicto.R;
import com.example.toja.dicto.models.Translation;

import java.util.stream.Collectors;

public class TranslationViewHolder extends RecyclerView.ViewHolder {

    TextView partOfSpeech, definition, examples;

    public TranslationViewHolder(@NonNull View itemView) {
        super(itemView);

        partOfSpeech = itemView.findViewById(R.id.part_of_speech);
        definition = itemView.findViewById(R.id.definition_txt_view);
        examples = itemView.findViewById(R.id.examples_txt_view);
    }

    public void onBind(Translation translation) {
        partOfSpeech.setText(translation.getPartOfSpeech());
        definition.setText(translation.getDefinition());
        examples.setText(returnExamples(translation));
    }

    String returnExamples(Translation translation) {
        if(translation.getExamples() != null) {
            return translation.getExamples().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\t"));
        }
        return "No examples";
    }
}
