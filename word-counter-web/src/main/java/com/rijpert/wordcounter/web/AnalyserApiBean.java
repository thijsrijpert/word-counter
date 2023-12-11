package com.rijpert.wordcounter.web;

import com.rijpert.wordcounter.lib.WordFrequency;
import com.rijpert.wordcounter.lib.WordFrequencyAnalyzer;
import com.rijpert.wordcounter.web.api.AnalyserApi;
import com.rijpert.wordcounter.web.dto.CalculateHighestFrequencyDTO;
import com.rijpert.wordcounter.web.dto.CalculateMostFrequentNWordsDTO;
import com.rijpert.wordcounter.web.dto.TextDTO;

import com.rijpert.wordcounter.web.dto.CalculateHighestFrequencyForWordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class AnalyserApiBean implements AnalyserApi {

    private static final Logger logger = LoggerFactory.getLogger(AnalyserApiBean.class);

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    public AnalyserApiBean(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

    @Override
    public CalculateHighestFrequencyForWordDTO calculateFrequencyForWord(String searchKey, TextDTO textDTO) {

        logger.info("Calculating the frequency of searchKey {} in text", searchKey);

        int frequency = wordFrequencyAnalyzer.calculateFrequencyForWord(textDTO.getContent(), searchKey);

        logger.info("Found frequency {} for searchKey {}", frequency, searchKey);

        return new CalculateHighestFrequencyForWordDTO()
                .frequency(frequency);
    }

    @Override
    public CalculateHighestFrequencyDTO calculateHighestFrequency(TextDTO textDTO) {

        logger.info("Calculating the searchKey with the highest frequency");

        int frequency = wordFrequencyAnalyzer.calculateHighestFrequency(textDTO.getContent());

        logger.info("Found the highest frequency {} for the text", frequency);

        return new CalculateHighestFrequencyDTO()
                .frequency(frequency);
    }

    @Override
    public List<CalculateMostFrequentNWordsDTO> calculateMostFrequentNWords(Integer limit, TextDTO textDTO) {
        logger.info("Calculating the top {} searchKeys with the highest frequency", limit);

        List<WordFrequency> results = wordFrequencyAnalyzer.calculateMostFrequentNWords(textDTO.getContent(), limit);
        AtomicInteger i = new AtomicInteger();

        return results.stream()
                .map(this::convertToDTO)
                .peek(word -> logger.info("Found frequency {} for the searchKey {}, n = {}", word.getFrequency(), word.getName(), i.getAndIncrement()))
                .toList();
    }

    private CalculateMostFrequentNWordsDTO convertToDTO(WordFrequency wordFrequency) {
        return new CalculateMostFrequentNWordsDTO()
                .frequency(wordFrequency.getFrequency())
                .name(wordFrequency.getWord());
    }
}
