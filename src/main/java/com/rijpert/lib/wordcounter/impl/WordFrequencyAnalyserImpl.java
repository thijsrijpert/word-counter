package com.rijpert.lib.wordcounter.impl;

import com.rijpert.lib.wordcounter.WordFrequency;
import com.rijpert.lib.wordcounter.WordFrequencyAnalyzer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class WordFrequencyAnalyserImpl implements WordFrequencyAnalyzer {

    @Override
    public int calculateHighestFrequency(String text) {
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
        return calculateFrequencyForWord(Text.parse(text), new Word(word));
    }

    private int calculateFrequencyForWord(Text text, Word word) {
        return Collections.frequency(text.words(), word);
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        return calculateMostFrequentNWords(Text.parse(text), n);
    }

    private List<WordFrequency> calculateMostFrequentNWords(Text text, int n) {
        return text.words().stream()
                .map(word -> WordFrequencyImpl.create(word, calculateFrequencyForWord(text, word)))
                .sorted(Comparator.comparingInt(WordFrequency::getFrequency))
                .limit(n)
                .collect(Collectors.toList());
    }
}
