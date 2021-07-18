import java.util.ArrayList;
import java.util.List;

public class MaxGeneticDiffQuery {
    // similar to "MaxXorWithElementFromArray". but here it's based on paths.
    // queries based on path: dfs and maintain a data structure. remove when we are done with the node. record which nodes are part of the query
    // dont necessarily need to sort the query but can do dfs in passing too!
    private Trie root = new Trie(-1);

    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int qn = queries.length;
        int n = parents.length;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        int troot = -1;
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) {
                troot = i;
                continue;
            }
            tree[parents[i]].add(i);
        }
        List<int[]>[] qs = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            qs[i] = new ArrayList<>();
        }
        for (int i = 0; i < qn; i++) {
            int[] q = queries[i];
            int node = q[0];
            qs[node].add(new int[]{q[1], i});
        }
        int[] res = new int[qn];
        dfs(troot, tree, qs, res);
        return res;
    }

    void dfs(int i, List<Integer>[] tree, List<int[]>[] qs, int[] res) {
        insert(i);
        for (int[] q : qs[i]) {
            //   System.out.println(i+" "+Arrays.toString(q));
            int cur = find(q[0]);
            res[q[1]] = cur;
        }
        for (int ne : tree[i]) {
            dfs(ne, tree, qs, res);
        }
        remove(i);
    }

    class Trie {
        private int val;
        private int nums = 0;
        private Trie[] ch = new Trie[2];

        public Trie(int val) {
            this.val = val;
        }
    }


    private void insert(int num) {
        Trie p = root;
        for (int j = 31; j >= 0; j--) {
            int dig = ((num >> j) & 1);
            if (p.ch[dig] == null) {
                p.ch[dig] = new Trie(dig);
            }
            p.ch[dig].nums++;
            p = p.ch[dig];
        }
    }

    private void remove(int num) {
        Trie p = root;
        for (int j = 31; j >= 0; j--) {
            int dig = ((num >> j) & 1);
            Trie next = p.ch[dig];
            next.nums--;
            if (next.nums == 0) {
                p.ch[dig] = null;
            }
            p = next;
        }
    }

    private int find(int num) {
        int res = 0;
        Trie p = root;
        for (int j = 31; j >= 0; j--) {
            int dig = ((num >> j) & 1);
            int nd = dig ^ 1;
            if (p.ch[nd] != null) {
                p = p.ch[nd];
                res |= (1 << j);
            } else {
                p = p.ch[dig];
            }
        }
        return res;
    }
}
