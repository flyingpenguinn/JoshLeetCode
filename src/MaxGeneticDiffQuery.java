
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxGeneticDiffQuery {
    class Trie {
        int v;
        Trie[] ch = new Trie[2];
        int cnt = 0;

        public Trie(int v) {
            this.v = v;
        }
    }

    private List<Integer>[] t;
    private Trie root = new Trie(-1);
    private Map<Integer, List<Integer>> qs = new HashMap<>();
    private Map<Integer, Map<Integer, Integer>> as = new HashMap<>();

    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        int root = -1;
        for (int i = 0; i < n; ++i) {
            int pi = parents[i];
            if (pi == -1) {
                root = i;
            } else {
                t[pi].add(i);
            }
        }
        for (int[] q : queries) {
            int v1 = q[0];
            int v2 = q[1];
            qs.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
        }

        dfs(root);
        int[] res = new int[queries.length];
        for (int i = 0; i < res.length; ++i) {
            int v1 = queries[i][0];
            int v2 = queries[i][1];
            res[i] = as.get(v1).get(v2);
        }
        return res;
    }

    private void dfs(int cur) {
        insertTrie(cur);
        for (int q : qs.getOrDefault(cur, List.of())) {
            int rv = checkTrie(q);
            as.computeIfAbsent(cur, p -> new HashMap<>()).put(q, rv);
        }
        for (int ne : t[cur]) {
            dfs(ne);
        }
        removeTrie(cur);
    }

    private int checkTrie(int v) {
        Trie p = root;
        int res = 0;
        for (int j = 31; j >= 0; --j) {
            int dig = (((v >> j) & 1));
            int rdig = dig ^ 1;
            if (p.ch[rdig] != null) {
                p = p.ch[rdig];
                res |= (1 << j);

            } else {
                p = p.ch[dig];
            }
        }
        return res;
    }

    private void insertTrie(int cur) {
        Trie p = root;
        for (int j = 31; j >= 0; --j) {
            int dig = (((cur >> j) & 1));
            if (p.ch[dig] == null) {
                p.ch[dig] = new Trie(dig);
            }
            ++p.ch[dig].cnt;
            p = p.ch[dig];
        }
    }


    private void removeTrie(int cur) {
        Trie p = root;
        for (int j = 31; j >= 0; --j) {
            int dig = (((cur >> j) & 1));
            --p.ch[dig].cnt;
            if (p.ch[dig].cnt == 0) {
                p.ch[dig] = null;
                break;
            } else {
                p = p.ch[dig];
            }
        }
    }
}
