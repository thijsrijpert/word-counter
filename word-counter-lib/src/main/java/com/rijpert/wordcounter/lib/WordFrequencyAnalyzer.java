package com.rijpert.wordcounter.lib;

import java.util.List;

/**
 * Utility interface to process words in a text.
 */
public interface WordFrequencyAnalyzer {

    /**
     * Calculate the frequency of the word that occurs the most in this text.
     * @param text The text that should be analysed, null is not accepted.
     * @return The frequency of the most common word.
     */
    int calculateHighestFrequency(String text);

    /**
     * Calculate the frequency of occurrence for a specific word in this text
     * @param text The text that should be analysed, null is not accepted
     * @param word The word that should be checked, null is not accepted
     * @return The frequency of the supplied word.
     */
    int calculateFrequencyForWord(String text, String word);

    /**
     * Calculate the frequency of the words that occur the most in this text.
     * If multiple members have the same frequency, it returns the word that is first in the alphabet
     * @param text The text that should be analysed, null is not accepted
     * @param n The amount of words to return
     * @return The n most frequent words in this text
     */
    List<WordFrequency> calculateMostFrequentNWords(String text, int n);

    /**
     * Create a new instance of the WordFrequencyAnalyzer
     * @return An instance of the WordFrequencyAnalyzer
     */
    static WordFrequencyAnalyzer create() {
        return new WordFrequencyAnalyserImpl();
    }
}
