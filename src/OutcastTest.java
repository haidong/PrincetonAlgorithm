import static org.junit.Assert.*;

import org.junit.Test;

public class OutcastTest {

    private final static String synsets = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/synsets.txt";
    private final static String hypernyms = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/hypernyms.txt";
    private final static String[] string5 = { "horse", "zebra", "cat", "bear", "table" };
    private final static String[] string8 = { "water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea",
            "coffee" };
    private final static String[] string11 = { "apple", "pear", "peach", "banana", "lime", "lemon", "blueberry",
            "strawberry", "mango", "watermelon", "potato" };

    @Test
    public void testOutcast5() {
        WordNet wordnet = new WordNet(synsets, hypernyms);
        Outcast outcast = new Outcast(wordnet);
        assertEquals("table", outcast.outcast(string5));
    }

    @Test
    public void testOutcast8() {
        WordNet wordnet = new WordNet(synsets, hypernyms);
        Outcast outcast = new Outcast(wordnet);
        assertEquals("bed", outcast.outcast(string8));
    }

    @Test
    public void testOutcast11() {
        WordNet wordnet = new WordNet(synsets, hypernyms);
        Outcast outcast = new Outcast(wordnet);
        assertEquals("potato", outcast.outcast(string11));
    }

}