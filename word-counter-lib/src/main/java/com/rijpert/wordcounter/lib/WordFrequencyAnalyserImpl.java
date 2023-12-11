package com.rijpert.wordcounter.lib;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


class WordFrequencyAnalyserImpl implements WordFrequencyAnalyzer {

    @Override
    public int calculateHighestFrequency(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text should not be null");
        }

        return calculateHighestFrequency(Text.parse(text));
    }

    private int calculateHighestFrequency(Text text) {
        return text.words().stream()
                .map(word -> calculateFrequencyForWord(text, word))
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("Empty text is used"));
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if (text == null) {
            throw new IllegalArgumentException("text should not be null");
        }

        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("word should not be null or empty");
        }

        return calculateFrequencyForWord(Text.parse(text), new Word(word));
    }

    private int calculateFrequencyForWord(Text text, Word word) {
        return Collections.frequency(text.words(), word);
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        if (text == null) {
            throw new IllegalArgumentException("text should not be null");
        }
        return calculateMostFrequentNWords(Text.parse(text), n);
    }

    private List<WordFrequency> calculateMostFrequentNWords(Text text, int n) {
        return text.words().stream()
                .distinct()
                .map(word -> WordFrequencyImpl.create(word, calculateFrequencyForWord(text, word)))
                .sorted()
                .limit(n)
                .collect(Collectors.toList());
    }
}
