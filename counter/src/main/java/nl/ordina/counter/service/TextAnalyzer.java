package nl.ordina.counter.service;

import lombok.extern.slf4j.Slf4j;
import nl.ordina.counter.model.WordItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TextAnalyzer {

    private final WordExtractor wordExtractor;
    private final ValidationService validationService;

    public TextAnalyzer(WordExtractor wordExtractor, ValidationService validationService) {
        this.wordExtractor = wordExtractor;
        this.validationService = validationService;
    }

    public int calculateHighestFrequency(String text) {
        validationService.validateText(text);
        return wordExtractor.calculateHighestFrequency(text);
    }

    public int calculateWordFrequency(String text, String word) {
        validationService.validateText(text);
        validationService.validateWord(word);
        return wordExtractor.calculateFrequencyForWord(text, word);
    }

    public List<WordItem> calculateMostPopularWords(String text, int topList) {
        validationService.validateText(text);
        validationService.validateNumber(topList);
        return wordExtractor.calculateMostFrequentNWords(text, topList);
    }
}
