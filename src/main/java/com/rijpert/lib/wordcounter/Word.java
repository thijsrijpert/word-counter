package com.rijpert.lib.wordcounter;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a single case-insensitive word, only ASCII letters are supported
 * @param word the ascii word
 */
record Word(String word, String wordLowerCase) {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z]+$");

    /**
     * Validate that the word only uses ascii letters
     * @param word the ascii word
     */
    public Word(String word) {
        this(word, word.toLowerCase());
        if(!PATTERN.matcher(word).matches()) {
            throw new IllegalArgumentException("A Word should only contain ASCII letters:" + word);
        }
    }

    public String word() {
        return wordLowerCase;
    }

    /**
     * Executes a case-insensitive compare between two words
     * @param o the reference object with which to compare.
     * @return true if the words are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word other = (Word)o;
        return this.word().equals(other.word());
    }

    /**
     * Get the hashcode for the word
     * @return the hashcode for the word, which is the same regardsless of case
     */
    @Override
    public int hashCode() {
        return Objects.hash(word());
    }
}
