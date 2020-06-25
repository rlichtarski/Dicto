package com.example.toja.dicto.util;

import com.example.toja.dicto.models.Translation;

import org.junit.jupiter.params.aggregator.ArgumentAccessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    Translation translation = new Translation();

    public static final List<String> TEST_WORDS_STRING_LIST = Collections.unmodifiableList(
            new ArrayList<String>(){{
                add("car");
                add("apple");
                add("head");
            }}
    );
}
