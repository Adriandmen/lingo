package com.adrianmensing.lingo.game;

import com.adrianmensing.lingo.game.guess.GuessResult;

public final class Word {
    private final String word;

    public Word(String word) {
        this.word = WordSanitizer.sanitize(word);
    }

    public int length() {
        return word.length();
    }

    public String contents() {
        return word;
    }

    public char[] chars() {
        return word.toCharArray();
    }

    public GuessResult makeGuess(Word guess) {
        if (word.length() != guess.length())
            throw new IllegalArgumentException("Expected guess word to have length " + word.length());

        return GuessResult.fromGuess(this, guess);
    }

    public static Word of(String word) {
        return new Word(word);
    }

    @Override
    public String toString() {
        return String.format("Word(%s)", word);
    }
}
