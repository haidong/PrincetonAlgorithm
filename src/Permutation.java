import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        String[] s = new String[k];
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        for (int i = 0; i < s.length; i++) {
           s[i] = rq.dequeue(); 
        }
        StdRandom.shuffle(s);
        for (int i = 0; i < s.length; i++) {
           StdOut.println(s[i]); 
        }
    }

}