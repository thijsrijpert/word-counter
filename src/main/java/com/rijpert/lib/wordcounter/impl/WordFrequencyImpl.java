package com.rijpert.lib.wordcounter.impl;

import com.rijpert.lib.wordcounter.WordFrequency;


/**
 * Class that contains a word and the frequency it has in a certain text.
 * @param word The word this frequency was calculated for.
 * @param frequency The frequency this word has in a text.
 */
public record WordFrequencyImpl(Word word, int frequency) implements WordFrequency {

    @Override
    public String getWord() {
        return word.word();
    }

    @Override
    public int getFrequency() {
        return frequency;
    }
}
