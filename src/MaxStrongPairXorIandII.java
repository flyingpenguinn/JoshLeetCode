import java.util.Arrays;

public class MaxStrongPairXorIandII {
    // trie to find max xor pair among a list of numbers
    private class Trie {
        int v;
        int count = 0;

        public Trie(int v) {
            this.v = v;
        }

        Trie[] ch = new Trie[2];
    }

    private Trie root = new Trie(-1);

    private void insert(int num) {
        Trie p = root;
        for (int i = 21; i >= 0; --i) {
            int bit = ((num >> i) & 1);
            if (p.ch[bit] == null) {
                p.ch[bit] = new Trie(bit);
            }
            ++p.ch[bit].count;
            p = p.ch[bit];
        }

    }

    private void remove(int num) {
        Trie p = root;
        for (int i = 21; i >= 0; --i) {
            int bit = ((num >> i) & 1);
            --p.ch[bit].count;
            if (p.ch[bit].count == 0) {
                p.ch[bit] = null;
                break;
            } else {
                p = p.ch[bit];
            }
        }
    }

    public int maximumStrongPairXor(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < n) {
            while (j < n && a[j] - a[i] <= a[i]) {
                insert(a[j]);
                ++j;
            }
            int cur = find(a[i]);
            res = Math.max(res, cur);
            remove(a[i]);
            ++i;
        }
        return res;
    }

    private int find(int num) {
        Trie p = root;
        int res = 0;
        for (int i = 21; i >= 0; --i) {
            int bit = ((num >> i) & 1);
            int nbit = (bit ^ 1);
            if (p.ch[nbit] != null) {
                p = p.ch[nbit];
                res |= (1 << i);
            } else {
                p = p.ch[bit];
            }
        }
        return res;
    }
}
