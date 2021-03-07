import java.util.Arrays;
import java.util.Comparator;

public class ShortEncodingOfWords {
    // suffix trie tree
    private class Trie {
        private char c;
        private Trie[] ch = new Trie[26];

        public Trie(char c) {
            this.c = c;
        }
    }

    private Trie root = new Trie('*');
    private int len = 0;
    private int count = 0;

    public int minimumLengthEncoding(String[] words) {
        for (String w : words) {
            if (insert(root, w)) {
                len += w.length();
                count++;
            }
        }
        return len + count;
    }

    private boolean insert(Trie n, String w) {
        Trie p = n;
        for (int i = w.length() - 1; i >= 0; i--) {
            char c = w.charAt(i);
            int cind = c - 'a';
            if (p.ch[cind] == null) {
                p.ch[cind] = new Trie(c);
            } else {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String[] words = {"time", "atime", "btime"};
        System.out.println(new ShortEncodingOfWords().minimumLengthEncoding(words));
    }
}
