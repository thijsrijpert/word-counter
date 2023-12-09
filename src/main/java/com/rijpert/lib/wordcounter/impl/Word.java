package com.rijpert.lib.wordcounter.impl;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a single case-insensitive word, only ASCII letters are supported
 * @param word the ascii word
 */
public record Word(String word) {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z]+$");

    /**
     * Validate that the word only uses ascii letters
     * @param word the ascii word
     */
    public Word {
        if(!PATTERN.matcher(word).matches()) {
            throw new IllegalArgumentException("A Word should only contain ASCII letters");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return this.word.equalsIgnoreCase(((Word)other).word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word.toLowerCase());
    }
}
