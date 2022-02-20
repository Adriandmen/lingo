package com.adrianmensing.lingo.game.response;

import com.adrianmensing.lingo.game.guess.GuessResult;

public class GuessAttemptedResponse implements GameResponse {
    private final GuessResult result;

    public GuessAttemptedResponse(GuessResult result) {
        this.result = result;
    }

    @Override
    public String message() {
        return result.toString();
    }

    @Override
    public GameStatus status() {
        return GameStatus.VALID;
    }
}
