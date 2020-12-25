public class NumberOfDistinctSubstrings {

    class Trie {
        private char c;
        private Trie[] ch = new Trie[26];

        public Trie(char c) {
            this.c = c;
        }
    }

    private Trie root = new Trie('#');

    public int countDistinct(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            Trie p = root;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                int cind = c - 'a';
                if (p.ch[cind] == null) {
                    p.ch[cind] = new Trie(c);
                    res++;
                }
                p = p.ch[cind];
            }
        }
        return res;
    }
}
