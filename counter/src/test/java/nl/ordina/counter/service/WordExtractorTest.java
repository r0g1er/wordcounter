package nl.ordina.counter.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WordExtractorTest {

    @InjectMocks
    private WordExtractor wordExtractor;

    private static Stream<Arguments> argumentsHighestFrequency() {
        return Stream.of(
                Arguments.of(4, "Deze-deze+deze dit dit deze a 45deze"),
                Arguments.of(5, "bb AA aa -aa -Aa- aA cc"),
                Arguments.of(3, " nu  nu   nu a d n "),
                Arguments.of(7, " c   c -- v c c c   c   -c")
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsHighestFrequency")
    void testCalculateHighestFrequency(int amountOfWords, String text) {
        var result = wordExtractor.calculateHighestFrequency(text);
        assertEquals(amountOfWords, result);
    }

    private static Stream<Arguments> argumentsCalculateFrequencyForWord() {
        return Stream.of(
                Arguments.of(5, "aa aa aaa bb aa bb aa BB bb cc BB", "bb"),
                Arguments.of(5, "aa aa aaa bb aa bb aa BB bb cc BB", "BB"),
                Arguments.of(5, "aa aa aaa bb aa bb aa BB bb cc BB", "bB"),
                Arguments.of(5, "aa aa aaa bb bbb bbbb aa bb aa BB bb cc BB", "bb")
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsCalculateFrequencyForWord")
    void testCalculateFrequencyForWord(int amountOfWords, String text, String word) {
        var result = wordExtractor.calculateFrequencyForWord(text, word);
        assertEquals(amountOfWords, result);
    }

    private static Stream<Arguments> argumentsCalculateMostFrequentNWord() {
        return Stream.of(
                Arguments.of("aa aa aaa bb aa bb aa BB bb cc BB", 10, 4, "bb", "cc"),
                Arguments.of("aa aa aaa bb aa bb aa BB bb cc BB", 2, 2, "bb", "aa"),
                Arguments.of("aa cc aa cc aa cc bb bb bb", 3, 3, "aa", "cc")
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsCalculateMostFrequentNWord")
    void calculateMostFrequentNWordsTest(String text, int n, int size, String first, String last) {
        var result = wordExtractor.calculateMostFrequentNWords(text, n);
        assertEquals(size, result.size());
        assertEquals(first, result.get(0).getWord());
        assertEquals(last, result.get(size - 1).getWord());
    }
}
