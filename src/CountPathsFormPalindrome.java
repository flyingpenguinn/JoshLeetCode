import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountPathsFormPalindrome {
    // we can totally query 1,2,4... ^ parts number and a hashmap to get the counts, but i like this trie solution too
    private Map<Integer, Integer>[] g;
    private long[] parts;

    private class Trie {
        int v;
        Trie[] ch = new Trie[2];
        int count = 0;

        public Trie(int v) {
            this.v = v;
        }
    }

    private Trie root = new Trie(-1);

    private void insert(long num) {
        Trie cur = root;
        for (int i = 0; i < 26; ++i) {
            int dig = (int) ((num >> i) & 1);
            if (cur.ch[dig] == null) {
                cur.ch[dig] = new Trie(dig);
            }
            cur = cur.ch[dig];
        }
        ++cur.count;
    }

    private long count(long num, int j) {
        Trie cur = root;
        for (int i = 0; i < 26; ++i) {
            int dig = (int) ((num >> i) & 1);
            if (i == j) {
                dig = dig ^ 1; // flip if we want to query j as the standalone
            }
            if (cur.ch[dig] == null) {
                return 0;
            }
            cur = cur.ch[dig];
        }
        return cur.count;
    }

    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        parts = new long[n];
        for (int i = 0; i < n; ++i) {
            int p = parent.get(i);
            if (p == -1) {
                continue;
            }
            Map<Integer, Integer> cm = g[p];
            cm.put(i, s.charAt(i) - 'a');
        }
        dfs(0, 0);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long curnum = parts[i];
            long curres = 0;
            for (int j = -1; j < 26; ++j) {
                long cur = count(curnum, j);
                curres += cur;
            }
            insert(curnum);
            res += curres;
        }
        return res;
    }

    private void dfs(int i, long num) {
        parts[i] = num;
        for (int ne : g[i].keySet()) {
            int cur = g[i].get(ne);
            long nnum = num ^ (1 << cur);
            dfs(ne, nnum);
        }
    }

    public static void main(String[] args) {
        System.out.println(new CountPathsFormPalindrome().countPalindromePaths(List.of(-1, 0, 0, 1, 1, 2), "acaabc"));
    }
}
