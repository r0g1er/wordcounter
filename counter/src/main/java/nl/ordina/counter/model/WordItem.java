package nl.ordina.counter.model;

import nl.ordina.counter.external.WordFrequency;

public class WordItem implements WordFrequency, Comparable<WordItem> {

    private final String word;
    private int frequency;

    public WordItem(String word) {
        this.word = word;
        frequency = 1;
    }

    public void match() {
        frequency++;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != WordItem.class) {
            return false;
        }
        return ((WordItem) obj).getWord().equals(this.word);
    }

    @Override
    public int compareTo(WordItem wordItem) {
        if (this.frequency == wordItem.frequency) {
            return this.word.compareTo(wordItem.word);
        }
        return Integer.compare(wordItem.frequency, this.frequency);
    }

    @Override
    public String toString() {
        return "Word: \"" + word + "\" has frequency: " + frequency;
    }
}
