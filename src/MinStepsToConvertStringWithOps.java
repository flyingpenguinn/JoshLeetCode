public class MinStepsToConvertStringWithOps {
    // TODO
    // note the letter paring technique
    public int minOperations(String word1, String word2) {
        int n = word1.length();
        final int INF = 1_000_000_000;
        // g[i][j] = min ops to turn word1[i..j] → word2[i..j]
        int[][] g = new int[n][n];
        // precompute for every substring [i..j]
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int costNoRev = calc(word1, word2, i, j, false);
                int costWithRev = calc(word1, word2, i, j, true) + 1;
                g[i][j] = Math.min(costNoRev, costWithRev);
            }
        }
        // f[k] = min ops to fix prefix word1[0..k-1]
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = INF;
            for (int j = 0; j < i; j++) {
                f[i] = Math.min(f[i], f[j] + g[j][i - 1]);
            }
        }
        return f[n];
    }

    // compute cost to turn word1[l..r] into word2[l..r],
    // optionally reversing the substring first
    private int calc(String w1, String w2, int l, int r, boolean rev) {
        int len = r - l + 1;
        char[] s = new char[len];
        // extract and maybe reverse
        if (!rev) {
            for (int k = 0; k < len; k++) s[k] = w1.charAt(l + k);
        } else {
            for (int k = 0; k < len; k++) s[k] = w1.charAt(r - k);
        }
        // two counters for mismatched letter-pairs
        int[][] fwd = new int[26][26];
        int[][] bwd = new int[26][26];
        for (int k = 0; k < len; k++) {
            int x = s[k] - 'a';
            int y = w2.charAt(l + k) - 'a';
            if (x < y) fwd[x][y]++;
            else if (x > y) bwd[y][x]++;
        }
        int ops = 0;
        // for each unordered pair (i,j), cost is max(fwd[i][j],bwd[i][j])
        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                ops += Math.max(fwd[i][j], bwd[i][j]);
            }
        }
        return ops;
    }
}

