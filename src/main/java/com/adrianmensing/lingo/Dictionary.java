package com.adrianmensing.lingo;

import com.adrianmensing.lingo.game.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Dictionary {

    private static Set<String> words;

    private static final String FILE_NAME = "dictionary.txt";

    private static void initialize() {
        InputStream stream = Dictionary.class.getClassLoader().getResourceAsStream(FILE_NAME);
        Objects.requireNonNull(stream);

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        reader.lines()
                .filter(word -> word.matches("^[a-z]{5}$"))
                .forEach(words::add);
    }

    public static synchronized Set<String> getDictionary() {
        if (words == null) {
            words = new HashSet<>();
            initialize();
        }

        return words;
    }

    public static Word getRandomWord() {
        int index = new Random().nextInt(getDictionary().size());

        Iterator<String> iterator = words.iterator();
        while (index-- > 0)
            iterator.next();

        return Word.of(iterator.next());
    }
}
