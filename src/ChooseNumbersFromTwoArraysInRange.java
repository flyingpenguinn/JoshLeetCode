import base.ArrayUtils;

public class ChooseNumbersFromTwoArraysInRange {
    // dp on i, balance

    private int MOD = (int) (1e9) + 7;
    private Long[][] dp = new Long[100][10010];

    // how many subarray starting from i will have sum==0 by this kind of choosing
    private long solve(int i, int sum, int[] a, int[] b) {
        int n = a.length;
        if (i == n) {
            return sum == 0 ? 1 : 0;
        }
        int cur = sum + 5000;
        if (cur > 10000 || cur < 0) {
            return 0;
        }
        if (dp[i][cur] != null) {
            return dp[i][cur];
        }
        long res = 0;
        if (sum == 0) {
            res += 1;
        }
        long way1 = solve(i + 1, sum + a[i], a, b) % MOD;
        long way2 = solve(i + 1, sum - b[i], a, b) % MOD;
        res += way1;
        res %= MOD;
        res += way2;
        res %= MOD;
        dp[i][cur] = res;
        return dp[i][cur];
    }

    public int countSubranges(int[] a, int[] b) {
        int n = a.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            // avoid counting this sum=0 right away in dfs...
            res += (solve(i + 1, a[i], a, b) + solve(i + 1, -b[i], a, b)) % MOD;
            res %= MOD;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(new ChooseNumbersFromTwoArraysInRange().countSubranges(ArrayUtils.read1d("1,2,5"), ArrayUtils.read1d("2,6,3")));
    }
}
