package com.rijpert.lib.wordcounter.impl;

import java.util.List;
import java.util.stream.Stream;

public record Text(List<Word> words) {

    public static Text parse(String text) {
        return new Text(Stream.of(text.split("\\W"))
                .map(Word::new)
                .toList());
    }
}
