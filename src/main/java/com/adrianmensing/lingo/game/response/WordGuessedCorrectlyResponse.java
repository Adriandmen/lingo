package com.adrianmensing.lingo.game.response;

public class WordGuessedCorrectlyResponse implements GameResponse {
    private final int turns;

    public WordGuessedCorrectlyResponse(int turns) {
        this.turns = turns;
    }

    @Override
    public String message() {
        return String.format("You have correctly guessed the word in %d turns", turns);
    }

    @Override
    public GameStatus status() {
        return GameStatus.COMPLETED;
    }
}
