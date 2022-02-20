package com.adrianmensing.lingo.game;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class WordSanitizerTest {

    @Test
    public void shouldThrowExceptionForNullInstances() {
        assertThatThrownBy(() -> WordSanitizer.sanitize(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldTrimWhitespaceCharacters() {
        assertThat(WordSanitizer.sanitize("  abde \n  ")).isEqualTo("abde");
    }

    @Test
    public void shouldConvertStringToLowercase() {
        assertThat(WordSanitizer.sanitize("HeLLo")).isEqualTo("hello");
    }

    @Test
    public void shouldThrowExceptionForWordsWithLengthSmallerThanThree() {
        assertThatThrownBy(() -> WordSanitizer.sanitize("ab")).isInstanceOf(IllegalArgumentException.class)
                                                              .hasMessageStartingWith("Expected")
                                                              .hasMessageContaining("at least three");
    }

    @Test
    public void shouldThrowExceptionForWordWithLengthSmallerThanThreeAfterTrimmed() {
        assertThatThrownBy(() -> WordSanitizer.sanitize("  ab ")).isInstanceOf(IllegalArgumentException.class)
                                                                 .hasMessageStartingWith("Expected")
                                                                 .hasMessageContaining("at least three");
    }

    @Test
    public void shouldThrowExceptionForNonAlphabeticWords() {
        assertThatThrownBy(() -> WordSanitizer.sanitize("abcd1e")).isInstanceOf(IllegalArgumentException.class)
                                                                  .hasMessageStartingWith("Expected")
                                                                  .hasMessageContaining("to contain alphabetic characters");
    }
}
