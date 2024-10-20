import java.util.*;

public class CheckIfDfsStringsArePalindromes {
    // AK last min !
    // use rolling hash to answer online palindrome question
    private Map<Integer, Set<Integer>> t = new HashMap<>();

    private class Item {
        long v1;
        long v2;
        long count;
        String str;

        public Item(long v1, long v2, long count, String str) {
            this.v1 = v1;
            this.v2 = v2;
            this.count = count;
            this.str = str;
        }
    }

    private boolean[] res;

    public boolean[] findAnswer(int[] parent, String s) {
        int n = parent.length;
        res = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (parent[i] != -1) {
                int cp = parent[i];
                t.computeIfAbsent(cp, k -> new TreeSet<>()).add(i);
            }
        }
        dfs(0, s);
        return res;
    }

    // seq1 value, seq value, num of nodes
    private Item dfs(int i, String s) {
        long v1 = 0;
        long c1 = 0;
        long v2 = 0;
        List<Item> rev = new ArrayList<>();
        // String cur = ""+s.charAt(i);
        for (int ne : t.getOrDefault(i, new TreeSet<>())) {
            Item prev = dfs(ne, s);
            v1 = merge(v1, c1, prev.v1);
            c1 += prev.count;
            rev.add(prev);
            //  cur += prev.str;
        }
        int c2 = 0;
        for (int j = rev.size() - 1; j >= 0; --j) {
            v2 = merge(v2, c2, rev.get(j).v2);
            c2 += rev.get(j).count;
        }
        v1 = merge(v1, c1, s.charAt(i) - 'a' + 1);
        v2 = merge(s.charAt(i) - 'a' + 1, 1, v2);
        if (v1 == v2) {
            res[i] = true;
        } else {
            res[i] = false;
        }
        return new Item(v1, v2, c1 + 1, "");
    }

    private long merge(long v1, long len1, long v2) {
        long pow = calcpow(base, len1);
        v2 *= pow;
        v2 %= Mod;
        long v = v1 + v2;
        v %= Mod;
        return v;
    }

    private long calcpow(long base, long p) {
        if (p == 0) {
            return 1L;
        }
        long half = calcpow(base, p / 2);
        long cur = half * half;
        cur %= Mod;
        if (p % 2 == 1) {
            cur *= base;
            cur %= Mod;
        }
        return cur;
    }

    private long base = 31;
    private long Mod = (long) (1e9 + 7);
}
