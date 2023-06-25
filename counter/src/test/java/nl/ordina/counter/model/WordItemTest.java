package nl.ordina.counter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordItemTest {

    @Test
    void testEquals() {
        var wordItem1 = new WordItem("woord");
        var wordItem2 = new WordItem("niks");
        var wordItem3 = new WordItem("woord");
        wordItem3.match();
        assertEquals(wordItem1, wordItem3);
        assertNotEquals(wordItem1, wordItem2);
    }

    @Test
    void testMatch() {
        var wordItem = new WordItem("test");
        wordItem.match();
        wordItem.match();
        assertEquals(3, wordItem.getFrequency());
    }

    @Test
    void testCompare() {
        var wordItem1 = new WordItem("a");
        var wordItem2 = new WordItem("b");
        var wordItem3 = new WordItem("d");
        var wordItem4 = new WordItem("a");
        wordItem2.match();
        assertTrue(wordItem1.compareTo(wordItem2) > 0);
        assertTrue(wordItem2.compareTo(wordItem3) < 0);
        assertTrue(wordItem1.compareTo(wordItem3) < 0);
        assertEquals(0, wordItem1.compareTo(wordItem4));
    }

    @Test
    void testToString() {
        var wordItem = new WordItem("foo");
        assertEquals("Word: \"foo\" has frequency: 1", wordItem.toString());
    }
}
