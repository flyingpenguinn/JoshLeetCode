import java.util.ArrayList;
import java.util.List;

public class ApplyOperationsToMakeStringsEqual {

    private int Max = (int) (1e9);
    private int x;
    private Integer[][] dp;

    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        dp = new Integer[n][n];
        this.x = x;
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                a.add(i);
            }
        }
        int[] ia = new int[a.size()];
        int pi = 0;
        for (int i = 0; i < a.size(); ++i) {
            ia[pi++] = a.get(i);
        }

        int res = solve(ia, 0, a.size() - 1);
        return res >= Max ? -1 : res;
    }

    private int solve(int[] a, int i, int j) {
        int n = a.length;
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return Max;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int way1 = a[i + 1] - a[i] + solve(a, i + 2, j);
        int way2 = a[j] - a[j - 1] + solve(a, i, j - 2);
        int way3 = x + solve(a, i + 1, j - 1);
        int res = Math.min(way1, Math.min(way2, way3));
        dp[i][j] = res;
        return res;
    }
}
