package com.adrianmensing.lingo.game;

import com.adrianmensing.lingo.Dictionary;
import com.adrianmensing.lingo.game.action.ActionHandler;
import com.adrianmensing.lingo.game.action.Action;
import com.adrianmensing.lingo.game.action.GuessAction;
import com.adrianmensing.lingo.game.guess.GuessResult;
import com.adrianmensing.lingo.game.response.GameResponse;
import com.adrianmensing.lingo.game.response.GuessAttemptedResponse;
import com.adrianmensing.lingo.game.response.InvalidWordLengthResponse;
import com.adrianmensing.lingo.game.response.WordGuessedCorrectlyResponse;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Game implements ActionHandler {
    public       GameState  state;
    public final Word       secretWord;
    public final List<Word> guesses;

    public Game() {
        this.state = GameState.IN_PROGRESS;
        this.secretWord = Dictionary.getRandomWord();
        this.guesses = new ArrayList<>();
    }

    @Override
    @NonNull
    public GameResponse handle(Action<?> action) {
        if (action instanceof GuessAction)
            return guess((GuessAction) action);

        throw new IllegalArgumentException("Could not handle event: " + action);
    }

    @NonNull
    private GameResponse guess(GuessAction action) {
        Word guess = action.data();

        if (guess.length() != secretWord.length())
            return new InvalidWordLengthResponse(guess, secretWord.length());

        GuessResult result = secretWord.makeGuess(guess);
        guesses.add(guess);

        if (result.isCompleted()) {
            this.state = GameState.COMPLETED;
            return new WordGuessedCorrectlyResponse(guesses.size());
        }

        return new GuessAttemptedResponse(result);
    }
}
