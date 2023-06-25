package nl.ordina.counter.service;

import nl.ordina.counter.Exceptions.InvalidUserInput;
import nl.ordina.counter.model.WordItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TextAnalyzerTest {

    @Mock
    private WordExtractor wordExtractor;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private TextAnalyzer textAnalyzer;

    @Test
    void testNoExceptionsAreCaught() {
        doThrow(new InvalidUserInput("testErrorText")).when(validationService).validateText(anyString());
        assertThrows(InvalidUserInput.class, () -> textAnalyzer.calculateHighestFrequency("test"));
        assertThrows(InvalidUserInput.class, () -> textAnalyzer.calculateWordFrequency("test", "test"));
        assertThrows(InvalidUserInput.class, () -> textAnalyzer.calculateMostPopularWords("test", 2));
    }

    @Test
    void testCalculateHighestFrequency() {
        when(wordExtractor.calculateHighestFrequency("test")).thenReturn(18);
        assertEquals(18, textAnalyzer.calculateHighestFrequency("test"));
    }

    @Test
    void testCalculateWordFrequency() {
        when(wordExtractor.calculateFrequencyForWord("test", "testWord")).thenReturn(8);
        assertEquals(8, textAnalyzer.calculateWordFrequency("test", "testWord"));
    }

    @Test
    void testCalculateMostPopularWords() {
        var result = List.of(new WordItem("word1"));
        when(wordExtractor.calculateMostFrequentNWords("text", 4)).thenReturn(result);
        assertEquals(result, textAnalyzer.calculateMostPopularWords("text", 4));
    }
}
