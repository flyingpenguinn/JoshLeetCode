import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class KthSmallestXorSum {
    static final int MAXB = 17;   // 2^17 > 10^5

    static class Node {
        Node[] nxt = new Node[2];
        boolean leaf;   // did a path-xor end exactly here?
        int cnt;        // count of distinct leaves in this subtree
    }

    int[] px, ans;

    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        int n = par.length;
        // build children lists
        List<Integer>[] ch = new ArrayList[n];
        for (int i = 0; i < n; i++) ch[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) ch[par[i]].add(i);

        // 1) compute path-xor from root
        px = new int[n];
        dfsXor(0, par, vals, ch);

        // 2) bucket queries by node
        @SuppressWarnings("unchecked")
        List<int[]>[] ql = new ArrayList[n];
        for (int i = 0; i < n; i++) ql[i] = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], k = queries[i][1];
            ql[u].add(new int[]{i, k});
        }
        ans = new int[queries.length];

        // 3) DSU-on-tree: build & merge tries, answer queries
        dfs(0, ch, ql);
        return ans;
    }

    // --- fix is right here ---
    private void dfsXor(int u, int[] par, int[] vals, List<Integer>[] ch) {
        if (u == 0) {
            px[u] = vals[u];
        } else {
            px[u] = px[par[u]] ^ vals[u];
        }
        for (int v : ch[u]) dfsXor(v, par, vals, ch);
    }

    // returns a trie of all distinct px[] in u's subtree
    private Node dfs(int u, List<Integer>[] ch, List<int[]>[] ql) {
        // start with just px[u]
        Node root = new Node();
        insert(root, px[u]);

        // merge in all children, small→large
        for (int v : ch[u]) {
            Node croot = dfs(v, ch, ql);
            if (croot.cnt > root.cnt) {
                Node tmp = root;
                root = croot;
                croot = tmp;
            }
            merge(root, croot);
        }

        // now answer queries at u
        for (int[] Q : ql[u]) {
            int qi = Q[0], k = Q[1];
            ans[qi] = root.cnt < k ? -1 : kth(root, k);
        }
        return root;
    }

    // insert x if new; update leaf+cnt
    private void insert(Node rt, int x) {
        Node cur = rt;
        Deque<Node> stk = new ArrayDeque<>();
        stk.push(cur);
        for (int b = MAXB - 1; b >= 0; b--) {
            int d = (x >> b) & 1;
            if (cur.nxt[d] == null) cur.nxt[d] = new Node();
            cur = cur.nxt[d];
            stk.push(cur);
        }
        if (!cur.leaf) {
            cur.leaf = true;
            // recompute cnt on the path back to root
            while (!stk.isEmpty()) {
                Node n = stk.pop();
                int c = n.leaf ? 1 : 0;
                if (n.nxt[0] != null) c += n.nxt[0].cnt;
                if (n.nxt[1] != null) c += n.nxt[1].cnt;
                n.cnt = c;
            }
        }
    }

    // OR-merge b into a, collapsing duplicates
    private Node merge(Node a, Node b) {
        if (b == null) return a;
        if (a == null) return b;
        a.leaf |= b.leaf;
        a.nxt[0] = merge(a.nxt[0], b.nxt[0]);
        a.nxt[1] = merge(a.nxt[1], b.nxt[1]);
        a.cnt = (a.leaf ? 1 : 0)
                + (a.nxt[0] != null ? a.nxt[0].cnt : 0)
                + (a.nxt[1] != null ? a.nxt[1].cnt : 0);
        return a;
    }

    // walk the trie to find the k-th smallest value
    private int kth(Node rt, int k) {
        int res = 0;
        Node cur = rt;
        for (int b = MAXB - 1; b >= 0; b--) {
            Node left = cur.nxt[0];
            int lc = (left != null ? left.cnt : 0);
            if (lc >= k) {
                cur = left;
            } else {
                k -= lc;
                res |= (1 << b);
                cur = cur.nxt[1];
            }
        }
        return res;
    }
}
