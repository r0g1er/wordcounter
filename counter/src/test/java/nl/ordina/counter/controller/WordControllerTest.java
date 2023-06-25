package nl.ordina.counter.controller;

import nl.ordina.counter.Exceptions.InvalidUserInput;
import nl.ordina.counter.model.WordItem;
import nl.ordina.counter.service.TextAnalyzer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordControllerTest {

    @Mock
    private TextAnalyzer textAnalyzer;

    @InjectMocks
    private WordController wordController;

    @Test
    void testHighestFrequency() {
        when(textAnalyzer.calculateHighestFrequency("bar")).thenReturn(7);

        var result = wordController.highestFrequency("bar");
        assertEquals("7", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testWordOccurrence() {
        when(textAnalyzer.calculateWordFrequency("bar", "foo")).thenReturn(42);

        var result = wordController.wordOccurrence("bar", "foo");
        assertEquals("42", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    void testGetMostPopularWords() {
        var response = List.of(new WordItem("FooBar"), new WordItem("towel"));
        when(textAnalyzer.calculateMostPopularWords("bar", 42)).thenReturn(response);

        var result = wordController.getMostPopularWords("bar", 42);
        assertEquals(response.toString(), result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testHighestFrequencyCatchInvalidUserInput() {
        when(textAnalyzer.calculateHighestFrequency("bar")).thenThrow(new InvalidUserInput("testError"));

        var result = wordController.highestFrequency("bar");
        assertEquals("testError", result.getBody());
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    void testWordOccurrenceCatchInvalidUserInput() {
        when(textAnalyzer.calculateWordFrequency("bar", "foo")).thenThrow(new InvalidUserInput("testError"));

        var result = wordController.wordOccurrence("bar", "foo");
        assertEquals("testError", result.getBody());
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    void testGetMostPopularWordsCatchInvalidUserInput() {
        when(textAnalyzer.calculateMostPopularWords("bar", 42)).thenThrow(new InvalidUserInput("testError"));

        var result = wordController.getMostPopularWords("bar", 42);
        assertEquals("testError", result.getBody());
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }
}
