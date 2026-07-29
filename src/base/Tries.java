package base;

public class Tries {
    class Trie {
        char c;
        Trie[] ch = new Trie[26];
        int count = 0;

        public Trie(char c) {
            this.c = c;
        }
    }

    private Trie t = new Trie('*');

    private void insert(String w) {
        int wn = w.length();
        Trie p = t;
        for (int i = 0; i < wn; ++i) {
            char c = w.charAt(i);
            int cind = c - 'a';
            if (p.ch[cind] == null) {
                p.ch[cind] = new Trie(c);
            }
            p = p.ch[cind];
        }
        p.count++;
    }

    private void remove(String w) {
        int wn = w.length();
        Trie p = t;
        for (int i = 0; i < wn; ++i) {
            char c = w.charAt(i);
            int cind = c - 'a';
            p = p.ch[cind];
        }
        p.count--;
    }

}
