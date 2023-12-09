package com.rijpert.lib.wordcounter;

import java.util.List;
import java.util.stream.Stream;

record Text(List<Word> words) {

    static Text parse(String text) {
        return new Text(Stream.of(text.split("\\W"))
                .map(Word::new)
                .toList());
    }
}
