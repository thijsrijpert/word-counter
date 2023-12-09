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

    /**
     * Executes a case-insensitive compare between two words
     * @param other the reference object with which to compare.
     * @return true if the words are equal
     */
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

    /**
     * Get the hashcode for the word
     * @return the hashcode for the word, which is the same regardsless of case
     */
    @Override
    public int hashCode() {
        return Objects.hash(word.toLowerCase());
    }
}
