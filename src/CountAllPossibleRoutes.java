public class CountAllPossibleRoutes {
    private long Mod = 1000000007;
    private Long[][] dp;

    public int countRoutes(int[] a, int start, int finish, int fuel) {
        dp = new Long[a.length][fuel + 1];
        return (int) doc(a, start, finish, fuel);
    }

    private long doc(int[] a, int i, int finish, int fuel) {
        //  System.out.println(i+" "+fuel);
        if (fuel < 0) {
            return 0;
        }
        if (dp[i][fuel] != null) {
            return dp[i][fuel];
        }
        long res = i == finish ? 1 : 0;
        for (int j = 0; j < a.length; j++) {
            if (j == i) {
                continue;
            }
            int delta = Math.abs(a[j] - a[i]);
            // distinct number so delta >0
            res += doc(a, j, finish, fuel - delta);
            res %= Mod;
        }
        dp[i][fuel] = res;
        return res;
    }
}
