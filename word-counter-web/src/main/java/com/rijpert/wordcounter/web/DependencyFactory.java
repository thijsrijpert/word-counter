package com.rijpert.wordcounter.web;

import com.rijpert.wordcounter.lib.WordFrequencyAnalyzer;
import org.springframework.context.annotation.Bean;

public class DependencyFactory {

    @Bean
    public WordFrequencyAnalyzer createWordFrequencyAnalyser() {
        return WordFrequencyAnalyzer.create();
    }
}
