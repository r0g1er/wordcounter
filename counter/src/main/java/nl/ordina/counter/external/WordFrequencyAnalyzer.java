package nl.ordina.counter.external;

import nl.ordina.counter.model.WordItem;

import java.util.List;

public interface WordFrequencyAnalyzer {
    int calculateHighestFrequency(String text);

    int calculateFrequencyForWord(String text, String word);

    List<WordItem> calculateMostFrequentNWords(String text, int n);
}
