import java.util.Arrays;

public class FindNumberOfKEventArrays {
    private long Mod = (long) (1e9 + 7);
    private long[] nums = new long[2];

    public int countOfArrays(int n, int m, int k) {
        nums[0] = m / 2;
        nums[1] = (m + 1) / 2;
        dp = new long[n][k + 1][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= k; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        long rt = nums[1] * solve(1, n, k, 1);

        rt += nums[0] * solve(1, n, k, 0);

        rt %= Mod;

        return (int) rt;
    }

    private long[][][] dp;

    // pre=0 means even
    private long solve(int i, int n, int k, int pre) {
        if (k < 0) {
            return 0;
        }
        if (i == n) {
            return k == 0 ? 1 : 0;
        }
        if (dp[i][k][pre] != -1) {
            return dp[i][k][pre];
        }
        long way1 = 0;
        if (pre == 0) {
            way1 = nums[0] * solve(i + 1, n, k - 1, 0);
        } else {
            way1 = nums[0] * solve(i + 1, n, k, 0);
        }
        long way2 = nums[1] * solve(i + 1, n, k, 1);
        long res = way1 + way2;
        res %= Mod;
        dp[i][k][pre] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FindNumberOfKEventArrays().countOfArrays(3, 4, 2));
        System.out.println(new FindNumberOfKEventArrays().countOfArrays(5,1,0));
        System.out.println(new FindNumberOfKEventArrays().countOfArrays(7, 7, 5));
    }
}
