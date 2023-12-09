package com.rijpert.lib.wordcounter.impl;

import java.util.Objects;
import java.util.regex.Pattern;

public record Word(String word) {

    private static final Pattern pattern = Pattern.compile("^[a-zA-Z]+$");

    public Word {
        if(!pattern.matcher(word).matches()) {
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
