package com.rijpert.wordcounter.lib;


import java.util.Comparator;

record WordFrequencyImpl(Word word, int frequency) implements WordFrequency, Comparable<WordFrequencyImpl> {

    @Override
    public String getWord() {
        return word.word();
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    static WordFrequency create(Word word, int frequency) {
        return new WordFrequencyImpl(word, frequency);
    }

    /**
     * Sort the frequency descending and, if equal the name ascending
     * @param o the object to be compared.
     * @return the result of the comparison
     */
    @Override
    public int compareTo(WordFrequencyImpl o) {
        return Comparator.comparingInt(WordFrequencyImpl::getFrequency)
                .reversed()
                .thenComparing(WordFrequencyImpl::getWord)
                .compare(this, o);
    }
}
