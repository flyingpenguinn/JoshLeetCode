import base.ArrayUtils;

import java.util.Arrays;

public class FindXvalueOfArrayI {

    public long[] resultArray(int[] a, int k) {
        int n = a.length;
        long[][] dp = new long[n][k];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            dp[i][v % k] = 1;
        }
        // dp[i][j]: ending at i, remainder = j
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                long mod = (1L * j * a[i]) % k;
                dp[i][(int) (mod)] += dp[i - 1][j];
            }
        }
        long[] res = new long[k];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                res[j] += dp[i][j];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindXvalueOfArrayI().resultArray(ArrayUtils.read1d("1,2,3,4,5"), 3)));
    }
}
