package com.rijpert.wordcounter.lib;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class WordFrequencyAnalyzerTest {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer = WordFrequencyAnalyzer.create();

    @Test
    void testCalculateHighestFrequencyNull() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            wordFrequencyAnalyzer.calculateHighestFrequency(null)
        );
    }

    @Test
    void testCalculateHighestFrequencyLorumIpsum() {
        String text = """
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
                    ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                    sunt in culpa qui officia deserunt mollit anim id est laborum.
                """;
        int result = wordFrequencyAnalyzer.calculateHighestFrequency(text);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void testCalculateHighestFrequencyWithCase() {
        String text = "The dog is walking in Dog City";
        int result = wordFrequencyAnalyzer.calculateHighestFrequency(text);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void testCalculateFrequencyForWordNullText() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            wordFrequencyAnalyzer.calculateFrequencyForWord(null, "Test")
        );
    }

    @Test
    void testCalculateFrequencyForWordNullWord() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            wordFrequencyAnalyzer.calculateFrequencyForWord("Test", null)
        );
    }

    @Test
    void testCalculateFrequencyForWordEmptyWord() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            wordFrequencyAnalyzer.calculateFrequencyForWord("Test", "")
        );
    }

    @Test
    void testCalculateFrequencyForWordLorumIpsum() {
        String text = """
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
                    ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                    sunt in culpa qui officia deserunt mollit anim id est laborum.
                """;
        int result = wordFrequencyAnalyzer.calculateFrequencyForWord(text, "dolore");

        assertThat(result).isEqualTo(2);
    }

    @Test
    void testCalculateFrequencyForWordUnicodeWord() {
        String text = "The dog is walking in Dog City";
        assertThatIllegalArgumentException().isThrownBy(() ->
            wordFrequencyAnalyzer.calculateFrequencyForWord(text, "ਠ"));
    }

    @Test
    void testCalculateFrequencyForWordUnicodeText() {
        String text = "The dog is walking in Dog Cityਠ";
        assertThatIllegalArgumentException().isThrownBy(() ->
                wordFrequencyAnalyzer.calculateFrequencyForWord(text, "ਠ"));
    }

    @Test
    void testCalculateFrequencyForWordCaseInsensitive() {
        String text = "The dog is walking in Dog City";
        int result = wordFrequencyAnalyzer.calculateFrequencyForWord(text, "Dog");

        assertThat(result).isEqualTo(2);
    }

    @Test
    void testCalculateMostFrequentNWordsNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            wordFrequencyAnalyzer.calculateMostFrequentNWords(null, 5);
        });
    }

    @Test
    void testCalculateMostFrequentNWordsLorumIpsum() {
        String text = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
                ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum.
                """;
        List<WordFrequency> result = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, 6);

        Assertions.assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(6);
        assertThat(result.get(0).getWord()).isEqualTo("i");
        assertThat(result.get(0).getFrequency()).isEqualTo(3);
        assertThat(result.get(1).getWord()).isEqualTo("ut");
        assertThat(result.get(1).getFrequency()).isEqualTo(3);
        assertThat(result.get(2).getWord()).isEqualTo("dolor");
        assertThat(result.get(2).getFrequency()).isEqualTo(2);
        assertThat(result.get(3).getWord()).isEqualTo("dolore");
        assertThat(result.get(3).getFrequency()).isEqualTo(2);
        assertThat(result.get(4).getWord()).isEqualTo("ad");
        assertThat(result.get(4).getFrequency()).isEqualTo(1);
        assertThat(result.get(5).getWord()).isEqualTo("adipiscing");
        assertThat(result.get(5).getFrequency()).isEqualTo(1);
    }

    @Test
    void testCalculateMostFrequentNWordsCaseInsensitive() {
        String text = "The dog is walking in Dog City";
        List<WordFrequency> result = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, 2);

        Assertions.assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getWord()).isEqualTo("dog");
        assertThat(result.get(0).getFrequency()).isEqualTo(2);
        Assertions.assertThat(result).isNotNull();
        assertThat(result.get(1).getWord()).isEqualTo("city");
        assertThat(result.get(1).getFrequency()).isEqualTo(1);
    }
}
