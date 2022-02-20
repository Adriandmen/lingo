package com.adrianmensing.lingo.game.action;

/**
 * Action interface.
 *
 * Describes action that can be performed during the game.
 *
 * @param <T> The type of data it holds.
 */
public interface Action<T> {
    String name();

    T data();
}
