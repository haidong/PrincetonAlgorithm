import edu.princeton.cs.algs4.ST;

public class Outcast {
    private WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns) {
        int sumOfDistance = 0;
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i = 0; i < nouns.length; i++) {
            for (int j = 0; j < nouns.length; j++) {
                if (i != j) {
                    sumOfDistance += wordnet.distance(nouns[i], nouns[j]);
                }
            }
            st.put(nouns[i], sumOfDistance);
            sumOfDistance = 0;
        }
        int distance = st.get(st.max());
        String answer = st.max();
        for (String s : st.keys()) {
            if (st.get(s) > distance) {
                distance = st.get(s);
                answer = s;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
    }

}
