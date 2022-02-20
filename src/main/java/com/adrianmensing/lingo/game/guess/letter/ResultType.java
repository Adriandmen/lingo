package com.adrianmensing.lingo.game.guess.letter;

import com.adrianmensing.console.Color;

public enum ResultType {
    CORRECT(Color.GREEN_BOLD),
    INCORRECT(Color.RED_BOLD),
    WRONG_PLACE(Color.YELLOW_BOLD),
    UNKNOWN(null);

    private final Color color;

    ResultType(Color color) {
        this.color = color;
    }

    public String colorize(Object text) {
        return color.colorize(text);
    }
}
