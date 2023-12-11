package com.rijpert.wordcounter.lib;

import java.util.List;
import java.util.stream.Stream;

record Text(List<Word> words) {

    static Text parse(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text should not be null");
        }
        return new Text(Stream.of(text.split("\\W")) //Split the string on punctuation characters
                .filter(word -> !word.isEmpty()) // Remove empty strings
                .map(Word::new) // Wrap the word inside a Word class
                .toList());
    }
}
