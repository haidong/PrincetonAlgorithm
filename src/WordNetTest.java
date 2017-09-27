import static org.junit.Assert.*;

import org.junit.Test;

public class WordNetTest {

    private final static String synsets6 = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/synsets6.txt";
    private final static String hypernym6TwoAncestors = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/hypernyms6TwoAncestors.txt";
    private final static String synsets = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/synsets.txt";
    private final static String hypernyms = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/hypernyms.txt";
    
    @Test(expected = NullPointerException.class)
    public void testConstructorException1() {
        WordNet wn = new WordNet(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorException2() {
        WordNet wn = new WordNet("hello", null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorException3() {
        WordNet wn = new WordNet(null, "hello");
    }

    @Test
    public void testNouns1() {
        WordNet wn = new WordNet(synsets6, hypernym6TwoAncestors);
        Iterable<String> ns = wn.nouns();
        
        int count = 0;
        for (String string : ns) {
           count++; 
        }
        
        assertEquals(6, count);
    }

    @Test
    public void testNouns2() {
        WordNet wn = new WordNet(synsets, hypernyms);
        Iterable<String> ns = wn.nouns();
        
        int count = 0;
        for (String string : ns) {
           count++; 
        }
        
        assertEquals(119188, count);
    }

    @Test
    public void testIsNoun1() {
        WordNet wn = new WordNet(synsets6, hypernym6TwoAncestors);
        
        assertTrue(wn.isNoun("a"));
    }

    @Test
    public void testIsNoun2() {
        WordNet wn = new WordNet(synsets6, hypernym6TwoAncestors);
        
        assertFalse(wn.isNoun("x"));
    }

    @Test
    public void testIsNoun3() {
        WordNet wn = new WordNet(synsets, hypernyms);
        
        assertTrue(wn.isNoun("Tabasco"));
        assertTrue(wn.isNoun("acquired_immune_deficiency_syndrome"));
    }

    @Test
    public void testDistance1() {
        WordNet wn = new WordNet(synsets6, hypernym6TwoAncestors);
        
        assertEquals(2, wn.distance("b", "f"));
    }

    @Test
    public void testSap1() {
        WordNet wn = new WordNet(synsets6, hypernym6TwoAncestors);
        
        assertEquals("e", wn.sap("d", "e"));
    }

    @Test
    public void testSap2() {
        WordNet wn = new WordNet(synsets6, hypernym6TwoAncestors);
        
        assertEquals("a", wn.sap("b", "f"));
    }

}
