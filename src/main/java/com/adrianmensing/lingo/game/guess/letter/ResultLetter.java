package com.adrianmensing.lingo.game.guess.letter;

public final class ResultLetter {
    public final char       letter;
    public final ResultType type;

    private ResultLetter(char letter, ResultType type) {
        this.letter = letter;
        this.type = type;
    }

    @Override
    public String toString() {
        char l = Character.toUpperCase(letter);

        if (this.type == ResultType.UNKNOWN)
            throw new IllegalStateException(String.format("Could not identify color for type '%s'", this.type));

        return type.colorize(l);
    }

    public static ResultLetter correct(char letter) {
        return new ResultLetter(letter, ResultType.CORRECT);
    }

    public static ResultLetter incorrect(char letter) {
        return new ResultLetter(letter, ResultType.INCORRECT);
    }

    public static ResultLetter wrongPlace(char letter) {
        return new ResultLetter(letter, ResultType.WRONG_PLACE);
    }
}
