public class LongestCommonSuffixQueires {
    class TrieNode {
        char c;
        TrieNode[] ch = new TrieNode[26];
        int best = -1;

        public TrieNode(char c) {
            this.c = c;
        }
    }

    private TrieNode root = new TrieNode('*');

    public int[] stringIndices(String[] a, String[] b) {
        int an = a.length;
        for (int i = 0; i < an; ++i) {
            insert(root, a, i);
        }
        int bn = b.length;
        int[] res = new int[bn];
        for (int i = 0; i < bn; ++i) {
            String bi = b[i];
            res[i] = query(root, bi);
        }
        return res;
    }

    private int query(TrieNode root, String bi) {
        TrieNode p = root;
        int sn = bi.length();
        for (int j = sn - 1; j >= 0; --j) {
            char c = bi.charAt(j);
            int cind = c - 'a';
            if (p.ch[cind] != null) {
                p = p.ch[cind];
            } else {
                break;
            }
        }
        return p.best;
    }

    private void insert(TrieNode root, String[] a, int i) {
        String ai = a[i];
        int sn = ai.length();
        TrieNode p = root;
        for (int j = sn - 1; j >= 0; --j) {
            updateBest(a, i, sn, p);
            char c = ai.charAt(j);
            int cind = c - 'a';
            if (p.ch[cind] == null) {
                p.ch[cind] = new TrieNode(c);
            }
            p = p.ch[cind];
        }
        updateBest(a, i, sn, p);
    }

    private void updateBest(String[] a, int i, int sn, TrieNode p) {
        if (p.best == -1) {
            p.best = i;
        } else if (a[p.best].length() > sn) {
            p.best = i;
        } else if (p.best > i) {
            p.best = i;
        }
    }
}
