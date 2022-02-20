package com.adrianmensing.lingo.game.action;

import com.adrianmensing.lingo.game.Word;

public class GuessAction implements Action<Word> {
    private final Word guess;

    public GuessAction(Word guess) {
        this.guess = guess;
    }

    @Override
    public String name() {
        return "guess";
    }

    @Override
    public Word data() {
        return guess;
    }
}
