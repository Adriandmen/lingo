package com.adrianmensing.lingo.game;

import java.util.Locale;
import java.util.Objects;

public class WordSanitizer {
    private WordSanitizer() {
    }

    public static String sanitize(String word) {
        Objects.requireNonNull(word);

        word = word.trim()
                   .toLowerCase(Locale.ROOT);

        if (word.length() < 3)
            throw new IllegalArgumentException(
                    String.format("Expected word to have at least three alphabetic characters, but got: '%s'", word));
        if (!word.matches("^[a-z]+"))
            throw new IllegalArgumentException(String.format("Expected word to contain alphabetic characters only, but got: '%s'", word));

        return word;
    }
}
