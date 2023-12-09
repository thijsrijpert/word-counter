package com.rijpert.lib.wordcounter;


record WordFrequencyImpl(Word word, int frequency) implements WordFrequency {

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
}
