package com.adrianmensing.lingo.game.guess;

import com.adrianmensing.lingo.game.Word;
import com.adrianmensing.lingo.game.guess.letter.ResultLetter;
import com.adrianmensing.lingo.game.guess.letter.ResultType;

import java.util.*;
import java.util.stream.Collectors;

public class GuessResult {
    private final List<ResultLetter> resultLetters;

    private GuessResult() {
        this.resultLetters = new ArrayList<>();
    }

    public List<ResultLetter> getResult() {
        return resultLetters;
    }

    public void addLetter(ResultLetter letter) {
        this.resultLetters.add(letter);
    }

    public boolean isCompleted() {
        return this.resultLetters.stream()
                                 .allMatch(letter -> letter.type == ResultType.CORRECT);
    }

    @Override
    public String toString() {
        return this.resultLetters.stream()
                                 .map(ResultLetter::toString)
                                 .collect(Collectors.joining(" "));
    }

    // -----------------------------------------------------------
    //  STATICS
    // -----------------------------------------------------------
    public static GuessResult fromGuess(Word secret, Word guess) {
        return fromGuess(secret.chars(), guess.chars());
    }

    public static GuessResult fromGuess(char[] secret, char[] guess) {
        assert secret.length == guess.length;

        final int               LENGTH    = secret.length;
        Map<Character, Integer> frequency = new HashMap<>();
        ResultType[]            types     = new ResultType[secret.length];
        Arrays.fill(types, ResultType.UNKNOWN);

        for (char c : secret) {
            int freq = frequency.getOrDefault(c, 0);
            frequency.put(c, freq + 1);
        }

        // Semi-greedy algorithm
        // first mark the correct letters
        for (int index = 0; index < LENGTH; index++) {
            if (secret[index] == guess[index]) {
                types[index] = ResultType.CORRECT;
                frequency.computeIfPresent(secret[index], (k, v) -> v - 1);
            }
        }

        // then mark remaining letters
        for (int index = 0; index < LENGTH; index++) {
            if (types[index] != ResultType.UNKNOWN)
                continue;

            if (frequency.getOrDefault(guess[index], 0) > 0) {
                types[index] = ResultType.WRONG_PLACE;
                frequency.computeIfPresent(guess[index], (k, v) -> v - 1);
            }
        }

        // mark remaining incorrect and collect results
        GuessResult result = new GuessResult();
        for (int index = 0; index < LENGTH; index++) {
            if (types[index] == ResultType.UNKNOWN) {
                types[index] = ResultType.INCORRECT;
            }

            switch (types[index]) {
                case CORRECT:
                    result.addLetter(ResultLetter.correct(guess[index]));
                    break;
                case INCORRECT:
                    result.addLetter(ResultLetter.incorrect(guess[index]));
                    break;
                case WRONG_PLACE:
                    result.addLetter(ResultLetter.wrongPlace(guess[index]));
                    break;
                default:
                    throw new IllegalStateException("What the fuck?");
            }
        }

        return result;
    }
}
