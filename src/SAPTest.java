import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;

public class SAPTest {
    private final static String D1INPUT = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/digraph1.txt";
    private final static String D2INPUT = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/digraph2.txt";
    private final static String D3INPUT = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/digraph3.txt";
    private final static String D9INPUT = "C:/Users/Haidong Ji/Downloads/wordnet-testing/wordnet/digraph9.txt";
    private final Digraph d1 = new Digraph(new In(D1INPUT));
    private final Digraph d2 = new Digraph(new In(D2INPUT));
    private final Digraph d3 = new Digraph(new In(D3INPUT));
    private final Digraph d9 = new Digraph(new In(D9INPUT));

    @Test(expected = NullPointerException.class)
    public void testConstructorException1() {
        SAP sap = new SAP(null);
    }

    @Test
    public void testAncestor1() {
        SAP sap = new SAP(d1);
        assertEquals(1, sap.ancestor(3, 4));
    }

    @Test
    public void testAncestor2() {
        SAP sap = new SAP(d1);
        assertEquals(1, sap.ancestor(3, 11));
    }

    @Test
    public void testAncestor3() {
        SAP sap = new SAP(d2);
        assertEquals(0, sap.ancestor(1, 5));
    }

    @Test
    public void testAncestor4() {
        SAP sap = new SAP(d2);
        assertEquals(4, sap.ancestor(3, 4));
    }

    @Test
    public void testAncestor5() {
        SAP sap = new SAP(d2);
        assertEquals(4, sap.ancestor(4, 4));
    }
    
    @Test
    public void testAncestor6() {
        SAP sap = new SAP(d1);
        assertEquals(5, sap.ancestor(9, 12));
    }

    @Test
    public void testAncestor7() {
        SAP sap = new SAP(d1);
        assertEquals(0, sap.ancestor(7, 2));
    }

    @Test
    public void testAncestor8() {
        SAP sap = new SAP(d1);
        assertEquals(-1, sap.ancestor(1, 6));
    }

    @Test
    public void testAncestor9() {
        SAP sap = new SAP(d3);
        assertEquals(-1, sap.ancestor(0, 1));
    }

    @Test
    public void testAncestor10() {
        SAP sap = new SAP(d9);
        assertEquals(2, sap.ancestor(2, 5));
    }

    @Test
    public void testLength1() {
        SAP sap = new SAP(d1);
        assertEquals(2, sap.length(3, 4));
    }

    @Test
    public void testLength2() {
        SAP sap = new SAP(d1);
        assertEquals(4, sap.length(3, 11));
    }

    @Test
    public void testLength3() {
        SAP sap = new SAP(d2);
        assertEquals(1, sap.length(4, 5));
    }

    @Test
    public void testLength4() {
        SAP sap = new SAP(d2);
        assertEquals(1, sap.length(3, 4));
    }

    @Test
    public void testLength5() {
        SAP sap = new SAP(d1);
        assertEquals(3, sap.length(9, 12));
    }

    @Test
    public void testLength6() {
        SAP sap = new SAP(d1);
        assertEquals(4, sap.length(7, 2));
    }

    @Test
    public void testLength7() {
        SAP sap = new SAP(d1);
        assertEquals(-1, sap.length(1, 6));
    }

    @Test
    public void testLength8() {
        SAP sap = new SAP(d3);
        assertEquals(-1, sap.length(0, 1));
    }

    @Test
    public void testLength9() {
        SAP sap = new SAP(d9);
        assertEquals(4, sap.length(2, 5));
    }

    @Test
    public void testLengthIterable1() {
        SAP sap = new SAP(d1);
        SET<Integer> set1 = new SET<Integer>();
        SET<Integer> set2 = new SET<Integer>();
        set1.add(3);
        set1.add(7);
        set2.add(4);
        set2.add(11);
        
        assertEquals(2, sap.length(set1, set2));
    }

    @Test
    public void testAncestorIterable1() {
        SAP sap = new SAP(d1);
        SET<Integer> set1 = new SET<Integer>();
        SET<Integer> set2 = new SET<Integer>();
        set1.add(3);
        set1.add(7);
        set2.add(4);
        set2.add(11);
        
        assertEquals(1, sap.ancestor(set1, set2));
    }

}