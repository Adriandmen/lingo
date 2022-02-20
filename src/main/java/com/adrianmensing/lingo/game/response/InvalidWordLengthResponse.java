package com.adrianmensing.lingo.game.response;

import com.adrianmensing.lingo.game.Word;

public class InvalidWordLengthResponse implements GameResponse {
    private final int expectedLength;
    private final Word receivedWord;

    public InvalidWordLengthResponse(Word receivedWord, int expectedLength) {
        this.receivedWord = receivedWord;
        this.expectedLength = expectedLength;
    }

    @Override
    public GameStatus status() {
        return GameStatus.RETRY;
    }

    @Override
    public String message() {
        return String.format("Incorrect word length, received word '%s' with length %d but expected a word with length %d",
                receivedWord.contents(),
                receivedWord.length(),
                expectedLength);
    }
}
