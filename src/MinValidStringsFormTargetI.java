import java.util.Arrays;

public class MinValidStringsFormTargetI {
    // just use trie! note each word can be 5k not 100!
    class Trie {
        int val;
        Trie[] ch = new Trie[26];

        public Trie(int v) {
            this.val = v;
        }
    }

    private Trie t = new Trie('*');

    private void insert(String s) {
        Trie p = t;
        for (int i = 0; i < s.length(); ++i) {
            int cind = s.charAt(i) - 'a';
            if (p.ch[cind] == null) {
                p.ch[cind] = new Trie(cind);
            }
            p = p.ch[cind];
        }
    }

    private int Max = (int) 1e9;

    public int minValidStrings(String[] words, String target) {
        for (String w : words) {
            insert(w);
        }
        dp = new int[target.length()];
        Arrays.fill(dp, -1);
        int rt = solve(target.toCharArray(), 0);
        return rt >= Max ? -1 : rt;
    }

    private int[] dp;

    private int solve(char[] s, int i) {
        int n = s.length;
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int res = Max;
        Trie p = t;
        for (int j = i; j < n; ++j) {
            int cind = s[j] - 'a';
            if (p.ch[cind] != null) {
                int later = solve(s, j + 1);
                int cur = later + 1;
                res = Math.min(res, cur);
                p = p.ch[cind];
            } else {
                break;
            }
        }
        dp[i] = res;
        return res;
    }
}
