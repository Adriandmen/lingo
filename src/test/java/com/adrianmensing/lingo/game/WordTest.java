package com.adrianmensing.lingo.game;

import com.adrianmensing.lingo.game.guess.GuessResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class WordTest {
    @Test
    public void shouldReturnWordInstanceForValidWord() {
        Word word = new Word("abcde");

        assertThat(word).isNotNull()
                        .isInstanceOf(Word.class);
    }

    @Test
    public void shouldReturnWordInstanceForValidWordFromStaticConstructor() {
        Word word = Word.of("abcde");

        assertThat(word).isNotNull()
                        .isInstanceOf(Word.class);
    }

    @Test
    public void shouldReturnTheCorrectLength() {
        Word word = Word.of("abcde");

        assertThat(word.length()).isEqualTo(5);
    }

    @Test
    public void shouldReturnTheContentsOfTheWord() {
        Word word = Word.of("abcde");

        assertThat(word.contents()).isEqualTo("abcde");
    }

    @Test
    public void shouldReturnTheCharactersOfTheWord() {
        Word word = Word.of("abcde");

        assertThat(word.chars()).hasSize(5)
                                .containsExactly('a', 'b', 'c', 'd', 'e');
    }

    @Test
    public void shouldThrowExceptionWhenGuessingWordWithIncorrectLength() {
        Word secret = Word.of("secret");
        Word guess  = Word.of("abcde");

        assertThatThrownBy(() -> secret.makeGuess(guess)).isInstanceOf(IllegalArgumentException.class)
                                                         .hasMessageStartingWith("Expected")
                                                         .hasMessageEndingWith("length " + secret.length());
    }

    @Test
    public void shouldReturnNormalGuessResultForValidGuess() {
        Word secret = Word.of("secret");
        Word guess  = Word.of("abcdef");

        assertThat(secret.makeGuess(guess)).isNotNull()
                                           .isInstanceOf(GuessResult.class);
    }

    @Test
    public void shouldReturnStringRepresentationOfWord() {
        Word word = Word.of("abcde");

        assertThat(word).asString().isEqualTo("Word(abcde)");
    }
}
