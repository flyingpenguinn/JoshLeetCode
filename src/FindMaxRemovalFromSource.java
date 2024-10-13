import java.util.Arrays;

public class FindMaxRemovalFromSource {
    // think of being in target as score[i] = 1, get the max score....
    private int[] target;
    private int MIN = -(int) 1e9;
    private int[][] dp;

    public int maxRemovals(String s, String p, int[] t) {
        int sn = s.length();
        int pn = p.length();
        target = new int[sn];
        for (int i = 0; i < t.length; ++i) {
            target[t[i]] = 1;
        }
        dp = new int[sn][pn + 1];
        for (int i = 0; i < sn; ++i) {
            Arrays.fill(dp[i], -1);
        }
        int maxremoval = solve(s, p, t, 0, 0);
        return Math.max(0, maxremoval);
    }

    // min cost to take elements
    private int solve(String s, String p, int[] t, int i, int j) {
        int sn = s.length();
        int pn = p.length();

        if (i == sn) {
            return j == pn ? 0 : Integer.MIN_VALUE;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int way1 = solve(s, p, t, i + 1, j) + target[i];
        int way2 = MIN;
        if (j < pn && s.charAt(i) == p.charAt(j)) {
            way2 = solve(s, p, t, i + 1, j + 1);
        }
        int res = Math.max(way1, way2);
        dp[i][j] = res;
        return res;
    }


    public static void main(String[] args) {
        final FindMaxRemovalFromSource sol = new FindMaxRemovalFromSource();
        System.out.println(sol.maxRemovals("bcda", "d", new int[]{0, 3}));
        // System.out.println(Arrays.toString(sol.gcdValues(ArrayUtils.read1d("[2,3,4]"), new long[]{0, 2, 2})));
    }
}
