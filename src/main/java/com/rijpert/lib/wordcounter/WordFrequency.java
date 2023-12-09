package com.rijpert.lib.wordcounter;

import com.rijpert.lib.wordcounter.impl.WordFrequencyImpl;

public interface WordFrequency {
    String getWord();
    int getFrequency();
}
