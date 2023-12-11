package com.rijpert.wordcounter.web;

import com.rijpert.wordcounter.lib.WordFrequencyAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DependencyFactory {

    @Bean
    public WordFrequencyAnalyzer createWordFrequencyAnalyser() {
        return WordFrequencyAnalyzer.create();
    }
}
