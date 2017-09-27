import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Topological;

public class WordNet {
    private ArrayList<String> synsetList = new ArrayList<String>();
    private SET<String> words = new SET<String>();
    private Digraph digraph;
    private SAP shortestAncestorPath;
    private Topological topological;

    public WordNet(String synsets, String hypernyms) {
        checkNulls(synsets, hypernyms);
        In in = new In(synsets);

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(",");
            int id = Integer.parseInt(a[0]);
            synsetList.add(id, a[1]);
            String[] b = a[1].split(" ");
            for (int i = 0; i < b.length; i++) {
                words.add(b[i]);
            }
        }

        digraph = new Digraph(synsetList.size());

        in = new In(hypernyms);
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(",");
            int v = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i++) {
                digraph.addEdge(v, Integer.parseInt(s[i]));
            }
        }
        topological = new Topological(digraph);
        if (!topological.hasOrder()) throw new IllegalArgumentException();

        shortestAncestorPath = new SAP(digraph);
    }

    public Iterable<String> nouns() {
        return words;
    }

    public boolean isNoun(String word) {
        if (word == null)
            throw new NullPointerException();
        return words.contains(word);
    }

    public int distance(String nounA, String nounB) {
        checkNulls(nounA, nounB);
        checkLegalNouns(nounA, nounB);
        Iterable<Integer> a = wordIndexList(nounA);
        Iterable<Integer> b = wordIndexList(nounB);
        return shortestAncestorPath.length(a, b);
    }

    private Iterable<Integer> wordIndexList(String noun) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < synsetList.size(); i++) {
            String[] temp = synsetList.get(i).split(" ");
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].equals(noun))
                    a.add(i);
            }
        }
        return a;
    }

    private void checkNulls(String a, String b) {
        if (a == null || b == null)
            throw new NullPointerException();
    }

    private void checkLegalNouns(String a, String b) {
        if (!isNoun(a) || !isNoun(b))
            throw new IllegalArgumentException();
    }

    public String sap(String nounA, String nounB) {
        checkNulls(nounA, nounB);
        checkLegalNouns(nounA, nounB);
        int a = shortestAncestorPath.ancestor(wordIndexList(nounA), wordIndexList(nounB));
        if (a == -1)
            return null;
        return synsetList.get(a);
    }

    public static void main(String[] args) {
    }

}