import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountNumberOfIdealArray {
    // if strictly increase length <=14
    // then use some combinatoric magic
    //TODO do it myself
    public int idealArrays(int n, int maxValue) {
        int m = 1000000007;
        BigInteger res = BigInteger.ZERO;
        long[][] dp = new long[15][maxValue + 1];
        Map<Integer, List<Integer>> map = buildMap(maxValue);

        // step 1: compute dp for the alternative problem (strictly increasing case)
        for (int i = 1; i <= maxValue; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n && i <= 14; i++) {
            for (int j = 1; j <= maxValue; j++) {
                for (int k : map.get(j)) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= m;
                }
            }
        }
        for (int i = 1; i <= n && i <= 14; i++) {
            for (int j = 1; j <= maxValue; j++) {
                dp[i][0] += dp[i][j];
                dp[i][0] %= m; // dp[i][0] = number of ideal arrays (strictly increasing case) of length i
            }
        }

        // step 2: use combinatorics to get the final answer for the actual problem from the alternative problem (strictly increasing case)
        for (int i = 1; i <= n && i <= 14; i++) {
            res = res.add(nCk(n - 1, i - 1).multiply(BigInteger.valueOf(dp[i][0])));
            res = res.mod(BigInteger.valueOf(m));
        }
        return res.intValue();
    }

    // helper function to compute "n choose k"
    private BigInteger nCk(int n, int k) {
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            res = res.multiply(BigInteger.valueOf(n - (i - 1))).divide(BigInteger.valueOf(i));
        }
        return res;
    }

    // helper funciton to build map {Integer -> {its divisors}}
    private Map<Integer, List<Integer>> buildMap(int maxValue) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= maxValue; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 1; i <= maxValue; i++) {
            int j = i * 2; // strictly increasing
            while (j <= maxValue) {
                map.get(j).add(i);
                j += i;
            }
        }
        return map;
    }
}
