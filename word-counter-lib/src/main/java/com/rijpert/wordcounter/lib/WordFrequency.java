package com.rijpert.wordcounter.lib;

/**
 * Class that contains a word and the frequency it has in a certain text.
 */
public interface WordFrequency {

    /**
     * Gets the word this frequency was calculated for
     * @return The word this frequency was calculated for.
     */
    String getWord();

    /**
     * Get the frequency this word has in a text.
     * @return The frequency this word has in a text.
     */
    int getFrequency();
}
