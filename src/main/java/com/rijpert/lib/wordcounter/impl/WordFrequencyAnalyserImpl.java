package com.rijpert.lib.wordcounter.impl;

import com.rijpert.lib.wordcounter.WordFrequency;
import com.rijpert.lib.wordcounter.WordFrequencyAnalyzer;

import java.util.List;

public class WordFrequencyAnalyserImpl implements WordFrequencyAnalyzer {


    @Override
    public int calculateHighestFrequency(String text) {
        return 0;
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        return 0;
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        return null;
    }
}
