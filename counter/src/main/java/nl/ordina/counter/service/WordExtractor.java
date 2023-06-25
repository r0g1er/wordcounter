package nl.ordina.counter.service;

import nl.ordina.counter.Exceptions.InvalidUserInput;
import nl.ordina.counter.external.WordFrequencyAnalyzer;
import nl.ordina.counter.model.WordItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordExtractor implements WordFrequencyAnalyzer {
    @Override
    public int calculateHighestFrequency(String text) {
        var sortedWords = calculateFrequencies(text);
        if (sortedWords.isEmpty()) {
            throw new InvalidUserInput("Text holds no valid words");
        }
        return sortedWords.get(0).getFrequency();
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        var sortedWords = calculateFrequencies(text);

        var position = sortedWords.indexOf(new WordItem(word.toLowerCase()));
        var result = sortedWords.get(position);
        return result.getFrequency();
    }

    @Override
    public List<WordItem> calculateMostFrequentNWords(String text, int n) {
        var sortedWords = calculateFrequencies(text);
        return sortedWords.subList(0, Integer.min(n, sortedWords.size()));
    }

    private List<WordItem> calculateFrequencies(String text) {
        var filteredText = extractAllWords(text);

        List<WordItem> aggregatedWordItems = new ArrayList<>();
        for (var item : filteredText) {
            var wordItem = new WordItem(item);
            if (aggregatedWordItems.contains(wordItem)) {
                var existingItem = aggregatedWordItems.get(aggregatedWordItems.indexOf(wordItem));
                existingItem.match();
            } else {
                aggregatedWordItems.add(wordItem);
            }
        }
        aggregatedWordItems.sort(WordItem::compareTo);
        return aggregatedWordItems;
    }

    private List<String> extractAllWords(String text) {
        var seperatedText = text.split("[\\s\\W]");
        ArrayList<String> filteredText = new ArrayList<>();
        for (String word : seperatedText) {
            var examinableWord = word.toLowerCase();
            if (isWord(examinableWord)) {
                filteredText.add(examinableWord);
            }
        }
        if (filteredText.isEmpty()) {
            throw new InvalidUserInput("Text holds no valid words");
        }
        return filteredText;
    }


    private boolean isWord(String word) {
        return word.matches("^[a-z]+");
    }
}
