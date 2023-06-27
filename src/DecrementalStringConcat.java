public class DecrementalStringConcat {
    private Integer[][][] dp;

    public int minimizeConcatenatedLength(String[] a) {
        int n = a.length;
        dp = new Integer[n][26][26];
        String first = a[0];
        return first.length() + solve(a, 1, first.charAt(0) - 'a', first.charAt(first.length() - 1) - 'a');
    }

    // j: first of the built
    // k: last of the built
    private int solve(String[] a, int i, int j, int k) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }
        String cur = a[i];
        int cfirst = cur.charAt(0) - 'a';
        int clast = cur.charAt(cur.length() - 1) - 'a';
        int way1 = 0;
        if (cfirst == k) {
            way1 = cur.length() - 1 + solve(a, i + 1, j, clast);
        } else {
            way1 = cur.length() + solve(a, i + 1, j, clast);
        }
        int way2 = 0;
        if (clast == j) {
            way2 = cur.length() - 1 + solve(a, i + 1, cfirst, k);
        } else {
            way2 = cur.length() + solve(a, i + 1, cfirst, k);
        }
        int res = Math.min(way1, way2);
        dp[i][j][k] = res;
        return res;
    }
}
