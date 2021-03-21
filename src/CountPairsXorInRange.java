public class CountPairsXorInRange {
    class Trie {
        private Trie[] ch = new Trie[2];
        private int v;
        private int count = 0;

        public Trie(int v) {
            this.v = v;
        }
    }

    private Trie root = new Trie(-1);

    public int countPairs(int[] a, int low, int high) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int cur = count(a[i], high) - count(a[i], low - 1);
            res += cur;
            insertTrie(root, a[i]);
        }
        return res;
    }

    private void insertTrie(Trie p, int v) {
        for (int i = 31; i >= 0; i--) {
            int bit = ((v >> i) & 1);
            if (p.ch[bit] == null) {
                p.ch[bit] = new Trie(v);
                p.ch[bit].count = 1;
            } else {
                p.ch[bit].count++;
            }
            p = p.ch[bit];
        }
    }

    private int count(int v1, int v2) {
        Trie p = root;
        int res = 0;
        for (int i = 31; i >= 0 && p != null; i--) {
            int bit2 = ((v2 >> i) & 1);
            int bit1 = ((v1 >> i) & 1);
            if (bit2 == 1) {
                res += p.ch[bit1] == null ? 0 : p.ch[bit1].count;
                p = p.ch[1 ^ bit1];
            } else {
                p = p.ch[bit1];
            }
        }
        return res + (p == null ? 0 : p.count);
    }
}
