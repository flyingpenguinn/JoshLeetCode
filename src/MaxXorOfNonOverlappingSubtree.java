import base.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxXorOfNonOverlappingSubtree {
    // gist is to calc node BEFORE calcing all the children, but insert value to trie AFTER all the children are done
    class TrieNode {
        private int val;
        private TrieNode[] ch = new TrieNode[2];

        public TrieNode(int val) {
            this.val = val;
        }
    }
    private int digits = 60;
    private Map<Integer, Set<Integer>> tree = new HashMap<>();
    private Map<Integer,Long> m = new HashMap<>();
    private long maxxor = 0;
    private TrieNode root = new TrieNode(-1);

    public long maxXor(int n, int[][] edges, int[] values) {
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
            tree.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }
        dfs1(0, -1, values);
        dfs2(0, -1, values);
        return maxxor;
    }

    private long dfs1(int i, int p, int[] values) {
        long res = values[i];
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            long cur = dfs1(ne, i, values);
            res += cur;
        }
        m.put(i, res);
        return res;
    }

    private void dfs2(int i, int p, int[] values) {
        long cv = m.get(i);
        long best = findBest(cv);
        maxxor = Math.max(maxxor, best);
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            dfs2(ne, i, values);
        }
        insertTrie(cv);
    }

    private long findBest(long num) {
        TrieNode cur = root;
        long best = 0;
        for (int i = digits; i >=0; --i) {
            int dig = (int) ((num >> i) & 1);
            int rdig = dig ^ 1;
            if (cur.ch[rdig] != null) {
                cur = cur.ch[rdig];
                best |= (1L << i);
            } else if(cur.ch[dig] != null){
                cur = cur.ch[dig];
            }else{
                // when trie is empty
                return 0;
            }
        }
        return best;
    }

    private void insertTrie(long num) {
        TrieNode cur = root;
        for (int i = digits; i >=0; --i) {
            int dig = (int) ((num >> i) & 1);
            if (cur.ch[dig] == null) {
                cur.ch[dig] = new TrieNode(dig);
            }
            cur = cur.ch[dig];
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaxXorOfNonOverlappingSubtree().maxXor(6, ArrayUtils.read("[[0,1],[0,2],[1,3],[1,4],[2,5]]"), ArrayUtils.read1d("[2,8,3,6,2,5]")));
    }
}
